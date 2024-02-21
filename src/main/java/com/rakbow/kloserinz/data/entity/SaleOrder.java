package com.rakbow.kloserinz.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.rakbow.kloserinz.data.enumable.OrderStatus;
import com.rakbow.kloserinz.data.enumable.SalesChannel;
import com.rakbow.kloserinz.helper.DateHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Rakbow
 * @since 2024/2/22 0:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sale_order")
public class SaleOrder extends MetaEntity {

    private Long id;
    private String serial;//订单序列号 SLO + 当前日期 + id
    private Long customerId;//客户id
    private double totalPrice;//总价
    private SalesChannel salesChannel;//销售市场
    private String creator;//录入人
    private String createDate;//录入时间
    private String dueDate;//订单截止时间
    private OrderStatus orderStatus;//订单状态

    public SaleOrder() {
        id = 0L;
        serial = "";
        customerId = 0L;
        totalPrice = 0;
        salesChannel = SalesChannel.DOMESTIC;
        creator = "";
        createDate = DateHelper.nowStr();
        dueDate = "";
        orderStatus = OrderStatus.NEW;
        setRemark("");
        setAddedTime(DateHelper.now());
        setEditedTime(DateHelper.now());
        setStatus(1);
    }

}
