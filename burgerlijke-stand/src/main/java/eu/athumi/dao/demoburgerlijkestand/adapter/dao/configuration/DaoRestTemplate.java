package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DaoRestTemplate {

    private final TokenManager tokenManager;

    public DaoRestTemplate(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Bean
    public RestTemplate oauth2RestTemplate() {
        return new RestTemplateBuilder()
                .defaultHeader("Authorization", "Bearer " + tokenManager.getAccessToken())
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        JavaTimeModule module = new JavaTimeModule();
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(module);
    }

}
