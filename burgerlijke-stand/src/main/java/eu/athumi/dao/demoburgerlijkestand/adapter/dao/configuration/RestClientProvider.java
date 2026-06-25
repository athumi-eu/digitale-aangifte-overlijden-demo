package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2AuthorizationFailureHandler;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.json.JsonMapper;

import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;
import static org.springframework.security.oauth2.client.web.ClientAttributes.clientRegistrationId;
import static org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor.authorizationFailureHandler;
import static org.springframework.security.oauth2.client.web.client.RequestAttributePrincipalResolver.principal;

@Component
public class RestClientProvider {

    private final ClientConfigurationProperties clientConfigurationProperties;
    private Map<String, RestClient> restClientMap = new HashMap<>();
    private final OAuth2AuthorizedClientManager backendClientManager;
    private final OAuth2AuthorizedClientService authorizedClientService;
    private final JsonMapper objectMapper;

    public RestClientProvider(ClientConfigurationProperties clientConfigurationProperties, OAuth2AuthorizedClientManager backendClientManager, OAuth2AuthorizedClientService authorizedClientService, JsonMapper objectMapper) {
        this.clientConfigurationProperties = clientConfigurationProperties;
        this.backendClientManager = backendClientManager;
        this.authorizedClientService = authorizedClientService;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        clientConfigurationProperties.getGemeentes().forEach(item -> restClientMap.put(item.getKbonummer(), securedWebClient(item.getKbonummer())));
    }

    public RestClient getRestClient(String kbonummer) {
        return ofNullable(restClientMap.get(kbonummer)).orElse(restClientMap.values().stream().findFirst().orElse(null));
    }

    public RestClient securedWebClient(String kbonummer) {
        org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor requestInterceptor =
                new OAuth2ClientHttpRequestInterceptor(backendClientManager);
        OAuth2AuthorizationFailureHandler authorizationFailureHandler = authorizationFailureHandler(
                authorizedClientService
        );
        requestInterceptor.setAuthorizationFailureHandler(authorizationFailureHandler);

        return RestClient.builder()
                .configureMessageConverters(converters -> {
                    converters.registerDefaults()
                                    .withJsonConverter(new JacksonJsonHttpMessageConverter(objectMapper));
                })
                .requestInterceptor(requestInterceptor)
                .defaultRequest(
                 item -> {
                     item.attributes(principal("backend-%s".formatted(kbonummer)))
                             .attributes(clientRegistrationId(kbonummer));
                 }
                ).build();
    }
}
