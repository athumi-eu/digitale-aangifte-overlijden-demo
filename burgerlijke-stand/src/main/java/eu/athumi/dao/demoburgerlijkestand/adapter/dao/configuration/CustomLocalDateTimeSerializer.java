package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CustomLocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    public CustomLocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializationContext ctxt) throws JacksonException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        String value = Optional.ofNullable(localDateTime)
                .map(item -> item.atZone(ZoneId.systemDefault()))
                .map(formatter::format)
                .orElse(null);

        jsonGenerator.writeString(value);
    }
}
