package com.rakbow.kloserinz.logic;

import com.alibaba.fastjson2.JSONObject;
import com.rakbow.kloserinz.data.ApiInfo;
import com.rakbow.kloserinz.entity.Inventory;
import com.rakbow.kloserinz.entity.SKU;
import com.rakbow.kloserinz.entity.Warehouse;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-21 3:24
 * @Description:
 */
public class BasicLogic {

    public static String checkSKU(JSONObject json) {
        if (StringUtils.isBlank(json.getString("code"))) {
            return ApiInfo.SKU_CODE_DEFICIENCY;
        }
        if (StringUtils.isBlank(json.getString("nameZh"))) {
            return ApiInfo.SKU_NAME_ZH_DEFICIENCY;
        }
        if (StringUtils.isBlank(json.getString("nameEn"))) {
            return ApiInfo.SKU_NAME_EN_DEFICIENCY;
        }
        return "";
    }

    //生成库存数据
    public static List<Inventory> generateInventory(List<Warehouse> nodes, SKU sku, double amount, double minimumSafeStock) {
        List<Inventory> inventories = new ArrayList<>();

        for (Warehouse node : nodes) {
            Inventory inv = new Inventory();
            inv.setSkuId(sku.getId());
            inv.setSkuName(sku.getNameZh());
            inv.setSkuCode(sku.getCode());
            inv.setNodeId(node.getId());
            inv.setNodeName(node.getName());
            inv.setNodeCode(node.getCode());
            inv.setAmount(amount);
            inv.setMinimumSafeStock(minimumSafeStock);
            inventories.add(inv);
        }

        return inventories;
    }

}
