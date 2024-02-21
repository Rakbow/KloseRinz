package com.rakbow.kloserinz.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.rakbow.kloserinz.data.enumable.CustomerRole;
import com.rakbow.kloserinz.helper.DateHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * @author Rakbow
 * @since 2024-02-21 23:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("company")
public class Company extends MetaEntity {

    private Long id;
    private String code;//编码
    private String name;//名称
    private String registerNo;//注册号
    private String taxNo;//税号
    private String country;//国家
    private String address;//地址
    private CustomerRole customerRole;//合作商类型 0-客户 1-供应商 2-分销商 99-其他
    private String postalCode;//邮政编号

    public Company() {
        id = 0L;
        code = "";
        name = "";
        registerNo = "";
        taxNo = "";
        country = "";
        address = "";
        customerRole = CustomerRole.OTHER;
        postalCode = "";
        setRemark("");
        setAddedTime(DateHelper.now());
        setEditedTime(DateHelper.now());
        setStatus(1);
    }

}
