package com.rakbow.kloserinz.data.dto.sku;

import com.rakbow.kloserinz.data.dto.DTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Rakbow
 * @since 2024/2/21 16:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SKUUpdateDTO extends DTO {

    private Long id;
    @NotBlank(message = "{sku.crud.code.required_field}")
    private String code; //编码
    @NotBlank(message = "{sku.crud.name_zh.required_field}")
    private String nameZh; //中文名
    @NotBlank(message = "{sku.crud.name_en.required_field}")
    private String nameEn; //英文名

    private String description; //介绍
    private long categoryId; //产品分类id
    private String spec; //规格
    private String unit; //单位

    private double purchasePriceCn;
    private double purchasePriceWw;
    private double sellingPriceCn;
    private double sellingPriceWw;

    private String remark; //备注

    public SKUUpdateDTO() {
        id = 0L;
        code = "";
        nameZh = "";
        nameEn = "";
        description = "";
        categoryId = 0;
        spec = "";
        unit = "";
        purchasePriceCn = 0;
        purchasePriceWw = 0;
        sellingPriceCn = 0;
        sellingPriceWw = 0;
        remark = "";
    }
}
