package com.rakbow.kloserinz.data.enumable;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Rakbow
 * @since 2024/2/22 0:27
 */
@Getter
@AllArgsConstructor
public enum SalesChannel {

    DOMESTIC(0, "国内"),
    OVERSEAS(1, "海外");

    @EnumValue
    private final Integer value;
    private final String label;

    public static SalesChannel get(int value) {
        for (SalesChannel type : SalesChannel.values()) {
            if(type.value == value)
                return type;
        }
        return DOMESTIC;
    }

}
