package com.rakbow.kloserinz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-17 1:40
 * @Description:
 */
@Data
@TableName("sku_category")
public class SKUCategory {

    private Long id;
    private String name;
    private int status;

    public SKUCategory() {
        id = 0L;
        name = "";
        status = 1;
    }

}
