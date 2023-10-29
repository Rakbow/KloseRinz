package com.rakbow.kloserinz.data;

import com.alibaba.fastjson2.JSONObject;

import java.util.List;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-28 3:53
 * @Description:
 */
public class MetaData {

    //sku分类选项
    public static List<Attribute<Long>> skuCategorySet;
    //仓库选项
    public static List<Attribute<Long>> supplyChainNodeSet;

    public static JSONObject toJson() {
        JSONObject res = new JSONObject();
        res.put("skuCategorySet", skuCategorySet);
        res.put("supplyChainNodeSet", supplyChainNodeSet);
        return res;
    }

}
