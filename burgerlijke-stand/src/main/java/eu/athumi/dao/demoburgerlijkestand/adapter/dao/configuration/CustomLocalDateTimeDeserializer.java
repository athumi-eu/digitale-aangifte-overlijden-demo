package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.deser.std.StdDeserializer;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CustomLocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    public CustomLocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, tools.jackson.databind.DeserializationContext ctxt) throws JacksonException {
        return Optional.ofNullable(jsonParser.getString())
                .map(s -> ZonedDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME))
                .map(z -> z.withZoneSameInstant(ZoneId.systemDefault()))
                .map(ZonedDateTime::toLocalDateTime)
                .orElse(null);
    }
}
