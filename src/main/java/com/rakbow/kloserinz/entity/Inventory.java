package com.rakbow.kloserinz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.rakbow.kloserinz.helper.DateHelper;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-17 1:49
 * @Description:
 */
@Data
@TableName("inventory")
public class Inventory {

    private Long skuId;
    private String skuCode;
    private String skuName;
    private Long nodeId;
    private String nodeCode;
    private String nodeName;
    private double amount;
    private double minimumSafeStock;
    private Timestamp addedTime;
    private Timestamp editedTime;
    private int status;

    public Inventory() {
        skuId = 0L;
        skuCode = "";
        skuName = "";
        nodeId = 0L;
        nodeCode = "";
        nodeName = "";
        amount = 0;
        minimumSafeStock = 0;
        addedTime = DateHelper.NOW_TIMESTAMP;
        editedTime = DateHelper.NOW_TIMESTAMP;
        status = 1;
    }

}
