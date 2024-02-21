package com.rakbow.kloserinz.data.enumable;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Rakbow
 * @since 2024-02-21 23:55
 */
@Getter
@AllArgsConstructor
public enum CustomerRole {

    PURCHASER(0, "客户"),
    SUPPLIER(1, "供应商"),
    DISTRIBUTOR(2, "分销商"),
    OTHER(99, "其他");

    @EnumValue
    private final Integer value;
    private final String label;

    public static CustomerRole get(int value) {
        for (CustomerRole role : CustomerRole.values()) {
            if(role.value == value)
                return role;
        }
        return OTHER;
    }

}
