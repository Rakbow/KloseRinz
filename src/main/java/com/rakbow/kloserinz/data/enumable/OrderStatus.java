package com.rakbow.kloserinz.data.enumable;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Rakbow
 * @since 2024/2/22 0:21
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {

    NEW(0, "新增"),
    COMPLETED(1, "已完成"),
    CANCELED(2, "取消"),
    OTHER(99, "其他");

    @EnumValue
    private final Integer value;
    private final String label;

    public static OrderStatus get(int value) {
        for (OrderStatus status : OrderStatus.values()) {
            if(status.value == value)
                return status;
        }
        return OTHER;
    }

}
