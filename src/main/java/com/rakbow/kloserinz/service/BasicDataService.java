package com.rakbow.kloserinz.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rakbow.kloserinz.dao.InventoryMapper;
import com.rakbow.kloserinz.dao.SKUCategoryMapper;
import com.rakbow.kloserinz.dao.SKUMapper;
import com.rakbow.kloserinz.dao.SupplyChainNodeMapper;
import com.rakbow.kloserinz.data.*;
import com.rakbow.kloserinz.entity.Inventory;
import com.rakbow.kloserinz.entity.SKU;
import com.rakbow.kloserinz.entity.SKUCategory;
import com.rakbow.kloserinz.entity.SupplyChainNode;
import com.rakbow.kloserinz.logic.BasicLogic;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-17 1:48
 * @Description:
 */
@Service
public class BasicDataService {

    //region Inject Bean
    private static final Logger logger = LoggerFactory.getLogger(BasicDataService.class);

    @Resource
    private SKUMapper skuMapper;
    @Resource
    private SKUCategoryMapper skuCategoryMapper;
    @Resource
    private InventoryMapper inventoryMapper;
    @Resource
    private SupplyChainNodeMapper supplyChainNodeMapper;
    //endregion

    //region SKU search

    //有条件分页查询SKU
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, readOnly = true)
    public ApiResult getSKU(QueryParams param) {
        ApiResult res = new ApiResult();
        try {
            //筛选条件
            QueryWrapper<SKU> wrapper = new QueryWrapper<SKU>()
                    .eq("status", 1)
                    .like("code", param.getStrFilter("code"))
                    .like("name_zh", param.getStrFilter("nameZh"))
                    .like("name_en", param.getStrFilter("nameEn"));
            List<Long> categoryIds = param.getArrFilter("categoryIds", Long.class);
            if (categoryIds != null && !categoryIds.isEmpty()) {
                wrapper.in("category_id", categoryIds);
            }
            if (!StringUtils.isBlank(param.sortField)) {
                if (param.sortOrder == 1) {
                    wrapper.orderByAsc(param.sortField);
                } else {
                    wrapper.orderByDesc(param.sortField);
                }
            }
            //筛选结果
            IPage<SKU> skuSet = skuMapper.selectPage(new Page<>(param.page, param.size), wrapper);
            res.data = skuSet.getRecords();
            res.total = skuSet.getTotal();
        } catch (Exception e) {
            res.setErrorMessage(e);
        }
        return res;
    }

    //新增SKU（单条）
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public void addSKU(JSONObject json) throws Exception {

        JSONObject skuJson = json.getJSONObject("sku");

        //数据检测
        String checkError = BasicLogic.checkSKU(skuJson);
        if (!StringUtils.isBlank(checkError))
            throw new Exception(checkError);
        //组装数据
        SKU sku = JSON.to(SKU.class, skuJson);

        //获取对应仓库
        QueryWrapper<SupplyChainNode> nodeWrapper = new QueryWrapper<>();
        nodeWrapper.in("id", json.getJSONArray("nodes").toJavaList(Integer.class));
        List<SupplyChainNode> nodes = supplyChainNodeMapper.selectList(nodeWrapper);

        //新增SKU
        long skuId = skuMapper.insert(sku);
        sku.setId(skuId);

        //生成库存数据
        List<Inventory> addInvSet = BasicLogic.generateInventory(nodes, sku, json.getIntValue("amount"), json.getDouble("minimumSafeStock"));
        //执行新增库存数据
        for (Inventory inv : addInvSet) {
            inventoryMapper.insert(inv);
        }
    }

    //更新SKU（单条）
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public int updateSKU(SKU sku) {
        return skuMapper.updateById(sku);
    }

    //endregion

    //region init data

    //加载MetaData

    public void loadMetaData() {
        //获取所有sku分类数据和仓库数据
        List<SKUCategory> skuCategories = skuCategoryMapper.selectList(new QueryWrapper<SKUCategory>().eq("status", 1));
        List<SupplyChainNode> SupplyChainNodes = supplyChainNodeMapper.selectList(new QueryWrapper<SupplyChainNode>().eq("status", 1));
        MetaData.skuCategorySet = new ArrayList<>();
        MetaData.supplyChainNodeSet = new ArrayList<>();
        skuCategories.forEach(category -> {
            MetaData.skuCategorySet.add(new Attribute<>(category.getName(), category.getId()));
        });
        SupplyChainNodes.forEach(node -> {
            MetaData.supplyChainNodeSet.add(new Attribute<>(node.getName(), node.getId()));
        });
        logger.info(ApiInfo.META_DATA_LOAD_SUCCESS);
    }

    //endregion
}
