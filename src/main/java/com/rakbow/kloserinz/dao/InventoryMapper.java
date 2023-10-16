package com.rakbow.kloserinz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rakbow.kloserinz.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-17 1:58
 * @Description:
 */
@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {
}
