package com.rakbow.kloserinz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.rakbow.kloserinz.helper.DateHelper;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-16 22:01
 * @Description:
 */
@Data
@TableName("sku")
public class SKU {

    private Long id; //编号
    private String code; //编码
    private String nameZh; //中文名
    private String nameEn; //英文名
    private String description; //介绍
    private long categoryId; //产品分类id
    private String categoryName; //产品分类名
    private String spec; //规格
    private double minimumSafeStock; //最小安全库存
    private double priceCn; //国内售价(RMB)
    private double priceWw; //海外售价(USD)
    private String remark; //备注
    private String originator; //录入人
    private Timestamp addedTime; //录入时间
    private Timestamp editedTime; //最后编辑时间
    private int status; //状态

    public SKU() {
        id = 0L;
        code = "";
        nameZh = "";
        nameEn = "";
        description = "";
        categoryId = 0;
        categoryName = "";
        spec = "";
        minimumSafeStock = 0;
        priceCn = 0;
        priceWw = 0;
        remark= "";
        originator = "";
        addedTime = DateHelper.NOW_TIMESTAMP;
        editedTime = DateHelper.NOW_TIMESTAMP;
        status = 1;
    }

}
