package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Value("${dao.service.frontend-base-url}")
    private String frontedBaseUrl;

    @Value("${dao.service.frontend-url}")
    private String frontedUrl;

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType("jsonld", new MediaType("application", "ld+json"));
        configurer.ignoreAcceptHeader(false);
    }

    @Bean
    public GlobalVariables globalVariables() {
        return new GlobalVariables(frontedBaseUrl, frontedUrl);
    }

    public record GlobalVariables(String frontedBaseUrl, String frontedUrl) {}

}
