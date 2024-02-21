package com.rakbow.kloserinz.util.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @author Rakbow
 * @since 2024/2/22 0:09
 */
public class BooleanToIntDeserializer extends JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
        boolean boolValue = p.getBooleanValue();
        return boolValue ? 1 : 0;
    }
}
