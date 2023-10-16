package com.rakbow.kloserinz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rakbow.kloserinz.dao.SKUMapper;
import com.rakbow.kloserinz.entity.SKU;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-16 22:13
 * @Description:
 */
@Service
public class TestService {

    @Resource
    private SKUMapper skuMapper;

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, readOnly = true)
    public List<SKU> getSKUList() {
        // 创建查询条件
        QueryWrapper<SKU> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);

        return skuMapper.selectList(queryWrapper);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public int addSKU(SKU sku) {
        return skuMapper.insert(sku);
    }

}
