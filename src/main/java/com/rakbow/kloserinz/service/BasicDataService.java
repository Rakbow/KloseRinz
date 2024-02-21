package com.rakbow.kloserinz.service;

import com.rakbow.kloserinz.dao.InventoryMapper;
import com.rakbow.kloserinz.dao.SKUCategoryMapper;
import com.rakbow.kloserinz.dao.SKUMapper;
import com.rakbow.kloserinz.dao.SupplyChainNodeMapper;
import com.rakbow.kloserinz.data.common.Attribute;
import com.rakbow.kloserinz.data.entity.SKUCategory;
import com.rakbow.kloserinz.data.entity.SupplyChainNode;
import com.rakbow.kloserinz.data.meta.MetaData;
import com.rakbow.kloserinz.util.I18nHelper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rakbow
 * @since 2023-10-17 1:48
 */
@Service
@RequiredArgsConstructor
public class BasicDataService {

    //region Inject Bean
    private static final Logger log = LoggerFactory.getLogger(BasicDataService.class);
    private final MetaData metaData;
    private final SKUCategoryMapper skuCategoryMapper;
    private final SupplyChainNodeMapper supplyChainNodeMapper;
    //endregion

    public void loadMetaData() {
        metaData.options.skuCategorySet.clear();
        metaData.options.nodeSet.clear();

        metaData.options.skuCategorySet.addAll(getSKUCategorySet());
        metaData.options.nodeSet.addAll(getNodeSet());

        log.info(I18nHelper.getMessage("system.load_data.meta_data"));
    }

    private List<Attribute<Long>> getSKUCategorySet() {
        List<Attribute<Long>> res = new ArrayList<>();
        List<SKUCategory> items = skuCategoryMapper.selectList(null);
        items.forEach(i -> res.add(new Attribute<>(i.getName(), i.getId())));
        return res;
    }

    private List<Attribute<Long>> getNodeSet() {
        List<Attribute<Long>> res = new ArrayList<>();
        List<SupplyChainNode> items = supplyChainNodeMapper.selectList(null);
        items.forEach(i -> res.add(new Attribute<>(i.getName(), i.getId())));
        return res;
    }

}
