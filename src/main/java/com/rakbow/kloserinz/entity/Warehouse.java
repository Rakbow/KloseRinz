package com.rakbow.kloserinz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.rakbow.kloserinz.helper.DateHelper;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-17 1:56
 * @Description:
 */
@Data
@TableName("warehouse")
public class Warehouse {

    private Long id;
    private String code;
    private String name;
    private Timestamp addedTime;
    private Timestamp editedTime;
    private int status;

    public Warehouse() {
        id = 0L;
        code = "";
        name = "";
        addedTime = DateHelper.NOW_TIMESTAMP;
        editedTime = DateHelper.NOW_TIMESTAMP;
        status = 1;
    }

}
