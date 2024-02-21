package com.rakbow.kloserinz.util.covertMapper;

import com.rakbow.kloserinz.data.dto.sku.SKUAddDTO;
import com.rakbow.kloserinz.data.entity.SKU;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * @author Rakbow
 * @since 2024/2/21 14:01
 */
@Mapper(componentModel = "spring")
public interface SKUVOMapper {

    SKUVOMapper INSTANCES = Mappers.getMapper(SKUVOMapper.class);

    @Named("build")
    SKU build(SKUAddDTO dto);

}
