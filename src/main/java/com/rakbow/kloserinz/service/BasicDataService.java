package com.rakbow.kloserinz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rakbow.kloserinz.dao.InventoryMapper;
import com.rakbow.kloserinz.dao.SKUCategoryMapper;
import com.rakbow.kloserinz.dao.SKUMapper;
import com.rakbow.kloserinz.dao.WarehouseMapper;
import com.rakbow.kloserinz.data.QueryParams;
import com.rakbow.kloserinz.entity.Inventory;
import com.rakbow.kloserinz.entity.SKU;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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

    //endregion

}
