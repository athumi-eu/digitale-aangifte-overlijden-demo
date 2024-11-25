package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.security.oauth2.client.registration.ClientRegistration.*;

@Configuration
public class ClientRegistrationConfig {

    private final ClientConfigurationProperties clientConfigurationProperties;

    public ClientRegistrationConfig(ClientConfigurationProperties clientConfigurationProperties) {
        this.clientConfigurationProperties = clientConfigurationProperties;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> collect = clientConfigurationProperties
                .getGemeentes()
                .stream()
                .map(item -> withRegistrationId(item.getKbonummer())
                        .clientId(item.getClientid())
                        .clientSecret(item.getClientsecret())
                        .tokenUri(clientConfigurationProperties.getTokenUri())
                        .issuerUri(clientConfigurationProperties.getIssuerUri())
                        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                        .clientAuthenticationMethod(clientConfigurationProperties.getAuthenticationMethod())
                        .scope("dao_lbbs")
                        .build())
                .collect(Collectors.toList());
        return new InMemoryClientRegistrationRepository(collect);
    }
}
