package com.rakbow.kloserinz.data;

/**
 * @Project_name: KloseRinz
 * @Author: Rakbow
 * @Create: 2023-10-27 2:05
 * @Description:
 */
public class  Attribute<T> {

    public String label;
    public T value;

    public Attribute() {

    }

    public Attribute(String label, T value) {
        this.label = label;
        this.value = value;
    }
}
