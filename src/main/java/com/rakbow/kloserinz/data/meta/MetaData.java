package com.rakbow.kloserinz.data.meta;

import org.springframework.stereotype.Component;

/**
 * @author Rakbow
 * @since 2024/2/21 14:50
 */
@Component
public class MetaData {

    public MetaOption options;

    public MetaData() {
        options = new MetaOption();
    }

}
