package com.rakbow.kloserinz.data.common;

import com.rakbow.kloserinz.data.dto.ListQry;

import java.util.LinkedHashMap;
import java.util.List;
import static com.rakbow.kloserinz.data.common.Constant.*;

/**
 * @author Rakbow
 * @since 2023-10-20 1:06 PrimeVue Common Query Param
 */
public class QueryParams {

    public int page;
    public int size;
    public String sortField;
    public int sortOrder;
    public LinkedHashMap<String, LinkedHashMap<String, Object>> filters;

    private static String VALUE_KEY = "value";

    public QueryParams() {
        page = 0;
        size = 0;
        sortField = "";
        sortOrder = 0;
        filters = new LinkedHashMap<>();
    }

    public boolean asc() {
        return this.sortOrder == 1;
    }

    public QueryParams(ListQry qty) {
        size = qty.getRows();
        page = qty.getFirst()/size + 1;
        sortField = qty.getSortField();
        sortOrder = qty.getSortOrder();
        filters = qty.getFilters();
    }

    public String getStr(String key) {
        Object value = this.filters.get(key).get(VALUE_KEY);
        if(value == null)
            return EMPTY;
        return value.toString();
    }

    public Boolean getBool(String key) {
        Object value = this.filters.get(key).get(VALUE_KEY);
        if(value == null)
            return null;
        return (Boolean) value;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getArray(String key) {
        Object value = this.filters.get(key).get(VALUE_KEY);
        if(value == null)
            return null;
        return (List<T>) value;
    }

}
