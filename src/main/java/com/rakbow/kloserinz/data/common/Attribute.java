package com.rakbow.kloserinz.data.common;

import lombok.Data;

/**
 * @author Rakbow
 * @since 2023-10-27 2:05
 */
@Data
public class Attribute<T> {

    private String label;
    private T value;

    public Attribute() {
        this.label = "";
        this.value = null;
    }

    public Attribute(String label, T value) {
        this.label = label;
        this.value = value;
    }

}
