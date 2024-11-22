package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Component
public class RestClientProvider {

    private final ClientConfigurationProperties clientConfigurationProperties;
    private Map<String, RestClient> restClientMap = new HashMap<>();
    private final OAuth2AuthorizedClientManager authorizedClientManager;
    private final OAuth2AuthorizedClientRepository authorizedClientRepository;

    public RestClientProvider(ClientConfigurationProperties clientConfigurationProperties, OAuth2AuthorizedClientManager authorizedClientManager, OAuth2AuthorizedClientRepository authorizedClientRepository) {
        this.clientConfigurationProperties = clientConfigurationProperties;
        this.authorizedClientManager = authorizedClientManager;
        this.authorizedClientRepository = authorizedClientRepository;
    }

    @PostConstruct
    public void init() {
        clientConfigurationProperties.getGemeentes().forEach(item -> restClientMap.put(item.getKbonummer(), securedWebClient(item.getKbonummer())));
    }

    public RestClient getRestClient(String kbonummer) {
        return ofNullable(restClientMap.get(kbonummer)).orElse(restClientMap.values().stream().findFirst().orElse(null));
    }
    public RestClient getRestClient(Model model) {
        return ofNullable(restClientMap.get(getKboNummerFrom(model))).orElse(restClientMap.values().stream().findFirst().orElse(null));
    }

    private String getKboNummerFrom(Model model) {
        return (String) model.getAttribute("kbonummer");
    }

    public RestClient securedWebClient(String kbonummer) {
        OAuth2ClientHttpRequestInterceptor requestInterceptor =
            new OAuth2ClientHttpRequestInterceptor(authorizedClientManager, kbonummer);
        requestInterceptor.setAuthorizedClientRepository(authorizedClientRepository);

        return RestClient.builder().requestInterceptor(requestInterceptor).build();
    }
}
