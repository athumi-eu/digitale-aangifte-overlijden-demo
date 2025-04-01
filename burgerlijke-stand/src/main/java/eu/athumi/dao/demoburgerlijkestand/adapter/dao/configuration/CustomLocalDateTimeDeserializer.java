package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
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
    public LocalDateTime deserialize(
        JsonParser jsonParser,
        DeserializationContext deserializationContext
    ) throws IOException, JacksonException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME.withZone(
            ZoneId.systemDefault()
        );
        return Optional.ofNullable(jsonParser.getText())
            .map(s -> ZonedDateTime.parse(s, formatter))
            .map(s -> s.withZoneSameInstant(ZoneId.systemDefault()))
            .map(ZonedDateTime::toLocalDateTime)
            .orElse(null);
    }
}
