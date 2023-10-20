package com.rakbow.kloserinz.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rakbow.kloserinz.dao.InventoryMapper;
import com.rakbow.kloserinz.dao.SKUCategoryMapper;
import com.rakbow.kloserinz.dao.SKUMapper;
import com.rakbow.kloserinz.dao.WarehouseMapper;
import com.rakbow.kloserinz.data.QueryParams;
import com.rakbow.kloserinz.entity.Inventory;
import com.rakbow.kloserinz.entity.SKU;
import com.rakbow.kloserinz.entity.Warehouse;
import com.rakbow.kloserinz.logic.BasicLogic;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
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
    @Resource
    private SKUMapper skuMapper;
    @Resource
    private SKUCategoryMapper skuCategoryMapper;
    @Resource
    private InventoryMapper inventoryMapper;
    @Resource
    private WarehouseMapper warehouseMapper;
    //endregion

    //region SKU search

    //有条件分页查询SKU
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, readOnly = true)
    public List<SKU> getSKU(QueryParams param) {
        //分页对象
        Page<SKU> page = new Page<>(param.page, param.size);
        // 创建查询条件
        QueryWrapper<SKU> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1)
                .like("code", param.getStrFilter("code"))
                .like("nameZh", param.getStrFilter("nameZh"))
                .like("nameEn", param.getStrFilter("nameEn"))
                .in("categoryId", param.getArrFilter("categoryIds", Long.class));
        if (!StringUtils.isBlank(param.sortField)) {
            if(param.sortOrder == 1) {
                wrapper.orderByAsc(param.sortField);
            }else {
                wrapper.orderByDesc(param.sortField);
            }
        }
        return skuMapper.selectList(wrapper);
    }

    //新增SKU（单条）
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public void addSKU(JSONObject json) throws Exception {

        JSONObject skuJson = json.getJSONObject("sku");

        //数据检测
        String checkError = BasicLogic.checkSKU(skuJson);
        if(!StringUtils.isBlank(checkError))
            throw new Exception(checkError);
        //组装数据
        SKU sku = JSON.to(SKU.class, skuJson);

        //获取对应仓库
        QueryWrapper<Warehouse> nodeWrapper = new QueryWrapper<>();
        nodeWrapper.in("id", json.getJSONArray("nodes").toJavaList(Integer.class));
        List<Warehouse> nodes = warehouseMapper.selectList(nodeWrapper);

        //新增SKU
        long skuId = skuMapper.insert(sku);
        sku.setId(skuId);

        //生成库存数据
        List<Inventory> addInvSet = BasicLogic.generateInventory(nodes, sku, json.getDouble("amount"), json.getDouble("minimumSafeStock"));
        //执行新增库存数据
        for(Inventory inv : addInvSet) {
            inventoryMapper.insert(inv);
        }
    }

    //更新SKU（单条）
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public int updateSKU(SKU sku) {
        return skuMapper.updateById(sku);
    }

    //endregion

}
