package com.rakbow.kloserinz.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.rakbow.kloserinz.helper.DateHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * @author Rakbow
 * @since 2023-10-16 22:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sku")
public class SKU extends MetaEntity {

    private Long id; //编号
    private String code; //编码
    private String nameZh; //中文名
    private String nameEn; //英文名
    private String description; //介绍
    private long categoryId; //产品分类id
    private String spec; //规格
    private String unit; //单位

    private double purchasePriceCn; //国内进货价(RMB)
    private double purchasePriceWw; //海外进货价(USD)
    private double sellingPriceCn; //国内售价(RMB)
    private double sellingPriceWw; //海外售价(USD)

    private String originator; //录入人

    public SKU() {
        id = 0L;
        code = "";
        nameZh = "";
        nameEn = "";
        description = "";
        categoryId = 0;
        spec = "";
        unit = "";
        sellingPriceCn = 0;
        sellingPriceWw = 0;
        originator = "";
        setRemark("");
        setAddedTime(DateHelper.now());
        setEditedTime(DateHelper.now());
        setStatus(1);
    }

}
