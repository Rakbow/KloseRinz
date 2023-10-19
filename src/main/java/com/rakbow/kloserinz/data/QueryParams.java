package com.rakbow.kloserinz.data;

import com.alibaba.fastjson2.JSONObject;

import java.util.List;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-20 1:06
 * @Description: PrimeVue Common Query Param
 */
public class QueryParams {

    public int page;
    public int size;
    public String sortField;
    public int sortOrder;
    public JSONObject filters;

    public QueryParams() {
        page = 0;
        size = 0;
        sortField = "";
        sortOrder = 0;
        filters = new JSONObject();
    }

    public QueryParams(JSONObject param) {
        JSONObject json = param.getJSONObject("queryParams");
        size = json.getIntValue("rows");
        page = json.getIntValue("first")/size + 1;
        sortField = json.getString("sortField");
        sortOrder = json.getIntValue("sortOrder");
        filters = json.getJSONObject("filters");
    }

    public String getStrFilter(String key) {
        return this.filters.getJSONObject(key).getString("value");
    }

    public <T> List<T> getArrFilter(String key, Class<T> clazz) {
        return this.filters.getJSONObject(key).getList("value", clazz);
    }

}
