package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.module.SimpleModule;

import java.time.LocalDateTime;

import static tools.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Configuration
public class DaoRestTemplate {

    @Bean
    public JsonMapperBuilderCustomizer customizer() {
        SimpleModule module = new SimpleModule("CustomDateHandler");
        module.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer());
        return builder -> {
            builder
                    .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                    .addModule(module);
        };

    }
}
