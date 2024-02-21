package com.rakbow.kloserinz.service;

import com.baomidou.mybatisplus.core.batch.MybatisBatch;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rakbow.kloserinz.dao.InventoryMapper;
import com.rakbow.kloserinz.dao.SKUCategoryMapper;
import com.rakbow.kloserinz.dao.SKUMapper;
import com.rakbow.kloserinz.dao.SupplyChainNodeMapper;
import com.rakbow.kloserinz.data.common.QueryParams;
import com.rakbow.kloserinz.data.common.SearchResult;
import com.rakbow.kloserinz.data.dto.sku.InventoryInfo;
import com.rakbow.kloserinz.data.dto.sku.SKUAddDTO;
import com.rakbow.kloserinz.data.dto.sku.SKUUpdateDTO;
import com.rakbow.kloserinz.data.entity.Inventory;
import com.rakbow.kloserinz.data.entity.SKU;
import com.rakbow.kloserinz.data.meta.MetaData;
import com.rakbow.kloserinz.util.covertMapper.SKUVOMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * SKU Service
 *
 * @author Rakbow
 * @since 2024/2/21 11:01
 */
@Service
@RequiredArgsConstructor
public class SKUService extends ServiceImpl<SKUMapper, SKU> {

    //region inject

    private static final Logger log = LoggerFactory.getLogger(SKUService.class);
    private final MetaData metaData;
    private final SKUVOMapper voMapper;
    private final SKUMapper mapper;
    private final InventoryMapper inventoryMapper;
    private final SKUCategoryMapper skuCategoryMapper;
    private final SupplyChainNodeMapper supplyChainNodeMapper;
    private final SqlSessionFactory sqlSessionFactory;

    //endregion

    @SneakyThrows
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, readOnly = true)
    public SearchResult<SKU> list(QueryParams param) {
        String code = param.getStr("code");
        String nameZh = param.getStr("nameZh");
        String nameEn = param.getStr("nameEn");
        //筛选条件
        LambdaQueryWrapper<SKU> wrapper = new LambdaQueryWrapper<SKU>()
                .eq(SKU::getStatus, 1)
                .like(StringUtils.isNotBlank(code), SKU::getCode, code)
                .like(StringUtils.isNotBlank(nameZh), SKU::getNameZh, nameZh)
                .like(StringUtils.isNotBlank(nameEn), SKU::getNameEn, nameEn);
        List<Long> categoryIds = param.getArray("categoryIds");
        if (CollectionUtils.isNotEmpty(categoryIds))
            wrapper.in(SKU::getCategoryId, categoryIds);
        if (StringUtils.isNotBlank(param.sortField)) {
            switch (param.sortField) {
                case "code" -> wrapper.orderBy(true, param.asc(), SKU::getCode);
                case "nameZh" -> wrapper.orderBy(true, param.asc(), SKU::getNameZh);
                case "nameEn" -> wrapper.orderBy(true, param.asc(), SKU::getNameEn);
            }
        }else {
            wrapper.orderByDesc(SKU::getId);
        }
        //筛选结果
        IPage<SKU> pages = mapper.selectPage(new Page<>(param.page, param.size), wrapper);
        return new SearchResult<>(pages);
    }

    @SneakyThrows
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public void add(SKUAddDTO dto) {

        //build sku
        SKU sku = voMapper.build(dto);
        //save sku
        long skuId = mapper.insert(sku);

        //generate inventory
        List<Inventory> addInvSet = new ArrayList<>();
        for(InventoryInfo info : dto.getInventoryInfos()) {
            addInvSet.add(new Inventory(info, skuId));
        }
        //TODO stock in info
        //save inventory
        MybatisBatch.Method<Inventory> method = new MybatisBatch.Method<>(InventoryMapper.class);
        MybatisBatch<Inventory> batchInsert = new MybatisBatch<>(sqlSessionFactory, addInvSet);
        batchInsert.execute(method.insert());
    }

    @SneakyThrows
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public void update(SKUUpdateDTO sku) {
        update(
                new LambdaUpdateWrapper<SKU>()
                        .eq(SKU::getId, sku.getId())
                        .set(SKU::getCode, sku.getCode())
                        .set(SKU::getNameZh, sku.getNameZh())
                        .set(SKU::getNameEn, sku.getNameEn())
                        .set(SKU::getDescription, sku.getDescription())
                        .set(SKU::getCategoryId, sku.getCategoryId())
                        .set(SKU::getSpec, sku.getSpec())
                        .set(SKU::getUnit, sku.getUnit())
                        .set(SKU::getPurchasePriceCn, sku.getPurchasePriceCn())
                        .set(SKU::getPurchasePriceWw, sku.getPurchasePriceWw())
                        .set(SKU::getSellingPriceCn, sku.getSellingPriceCn())
                        .set(SKU::getSellingPriceWw, sku.getSellingPriceWw())
                        .set(SKU::getRemark, sku.getRemark())
        );
    }

    @SneakyThrows
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public void delete(List<Long> ids) {

    }

    //endregion

}
