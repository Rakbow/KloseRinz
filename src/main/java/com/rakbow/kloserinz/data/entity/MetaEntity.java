package com.rakbow.kloserinz.data.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rakbow.kloserinz.helper.DateHelper;
import com.rakbow.kloserinz.util.jackson.BooleanToIntDeserializer;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Rakbow
 * @since 2024/2/22 0:10
 */
@Data
public class MetaEntity {

    private String remark;//备注
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateHelper.DATE_TIME_FORMAT, timezone="GMT+8")
    private Timestamp addedTime;//数据新增时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateHelper.DATE_TIME_FORMAT, timezone="GMT+8")
    private Timestamp editedTime;//数据更新时间
    @JsonDeserialize(using = BooleanToIntDeserializer.class)
    private int status;//激活状态

}
