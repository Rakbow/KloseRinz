package com.rakbow.kloserinz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rakbow.kloserinz.data.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Rakbow
 * @since 2023-10-17 1:58
 */
@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {
}
