package com.rakbow.kloserinz.data.meta;

import com.rakbow.kloserinz.data.common.Attribute;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rakbow
 * @since 2024/2/21 14:48
 */
public class MetaOption {

    public List<Attribute<Long>> skuCategorySet;
    public List<Attribute<Long>> nodeSet;

    public MetaOption() {
        skuCategorySet = new ArrayList<>();
        nodeSet = new ArrayList<>();
    }

}
