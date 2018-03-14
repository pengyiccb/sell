package com.yaoer.seller.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.Data;

import java.io.IOException;
import java.util.Date;

/**
 * Created by lenovo on 2018/2/25.
 */
public class Date2LongSerlalizer extends JsonSerializer<Date>{
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            gen.writeNumber(     value.getTime()/10000);
    }
}
