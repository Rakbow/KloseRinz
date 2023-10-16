package com.rakbow.kloserinz.service;

import com.rakbow.kloserinz.dao.InventoryMapper;
import com.rakbow.kloserinz.dao.SKUCategoryMapper;
import com.rakbow.kloserinz.dao.SKUMapper;
import com.rakbow.kloserinz.dao.WarehouseMapper;
import com.rakbow.kloserinz.entity.Inventory;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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

}
