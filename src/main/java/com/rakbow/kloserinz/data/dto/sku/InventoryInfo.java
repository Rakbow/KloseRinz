package com.rakbow.kloserinz.data.dto.sku;

import com.rakbow.kloserinz.data.dto.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Rakbow
 * @since 2024/2/21 15:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InventoryInfo extends DTO {

    private Long skuId;
    private Long nodeId;
    private double amount;
    private double minimumSafeStock;

    public InventoryInfo() {
        skuId = 0L;
        nodeId = 0L;
        amount = 0;
        minimumSafeStock = 0;
    }

}
