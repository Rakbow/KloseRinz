package com.rakbow.kloserinz.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.rakbow.kloserinz.data.dto.sku.InventoryInfo;
import com.rakbow.kloserinz.helper.DateHelper;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Rakbow
 * @since 2023-10-17 1:49
 */
@Data
@TableName("inventory")
public class Inventory {

    private Long skuId;
    private Long nodeId;
    private double amount;
    private double minimumSafeStock;

    public Inventory() {
        skuId = 0L;
        nodeId = 0L;
        amount = 0;
        minimumSafeStock = 0;
    }

    public Inventory(InventoryInfo info, long skuId) {
        this.skuId = skuId;
        nodeId = info.getNodeId();
        amount = info.getAmount();
        minimumSafeStock = info.getMinimumSafeStock();
    }

}
