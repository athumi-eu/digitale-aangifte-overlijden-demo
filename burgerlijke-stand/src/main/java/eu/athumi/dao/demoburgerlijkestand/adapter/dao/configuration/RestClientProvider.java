package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

@Component
public class RestClientProvider {

    private final ClientConfigurationProperties clientConfigurationProperties;
    private Map<String, RestClient> restClientMap = new HashMap<>();
    private final OAuth2AuthorizedClientManager authorizedClientManager;
    private final OAuth2AuthorizedClientRepository authorizedClientRepository;
    private final ObjectMapper objectMapper;

    public RestClientProvider(ClientConfigurationProperties clientConfigurationProperties, OAuth2AuthorizedClientManager authorizedClientManager, OAuth2AuthorizedClientRepository authorizedClientRepository, ObjectMapper objectMapper) {
        this.clientConfigurationProperties = clientConfigurationProperties;
        this.authorizedClientManager = authorizedClientManager;
        this.authorizedClientRepository = authorizedClientRepository;
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
        OAuth2ClientHttpRequestInterceptor requestInterceptor =
            new OAuth2ClientHttpRequestInterceptor(authorizedClientManager, kbonummer);
        requestInterceptor.setAuthorizedClientRepository(authorizedClientRepository);

        return RestClient.builder().messageConverters(c -> {
            c.removeIf(MappingJackson2HttpMessageConverter.class::isInstance);
            c.add(new MappingJackson2HttpMessageConverter(objectMapper));
        }).requestInterceptor(requestInterceptor).build();
    }
}
