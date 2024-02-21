package com.rakbow.kloserinz.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.rakbow.kloserinz.helper.DateHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * @author Rakbow
 * @since 2023-10-17 1:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("supply_chain_node")
public class SupplyChainNode extends MetaEntity {

    private Long id;
    private String code;
    private String name;

    public SupplyChainNode() {
        id = 0L;
        code = "";
        name = "";
        setRemark("");
        setAddedTime(DateHelper.now());
        setEditedTime(DateHelper.now());
        setStatus(1);
    }

}
