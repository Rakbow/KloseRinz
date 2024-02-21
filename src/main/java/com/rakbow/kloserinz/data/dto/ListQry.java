package com.rakbow.kloserinz.data.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedHashMap;

/**
 * @author Rakbow
 * @since 2024/2/21 11:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListQry extends Query {

    private int first;
    private int rows;
    private String sortField;
    private int sortOrder;
    private LinkedHashMap<String, LinkedHashMap<String, Object>> filters;

}
