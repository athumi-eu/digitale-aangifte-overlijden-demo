package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient securedWebClient(
        OAuth2AuthorizedClientManager authorizedClientManager,
        OAuth2AuthorizedClientRepository authorizedClientRepository
    ) {
        OAuth2ClientHttpRequestInterceptor requestInterceptor =
            new OAuth2ClientHttpRequestInterceptor(authorizedClientManager, "demo");
        requestInterceptor.setAuthorizedClientRepository(authorizedClientRepository);

        return RestClient.builder().requestInterceptor(requestInterceptor).build();
    }
}
