package com.rakbow.kloserinz.data.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author Rakbow
 * @since 2024/2/21 11:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonCommand extends Command {

    @Serial
    private static final long serialVersionUID = 1L;

}
