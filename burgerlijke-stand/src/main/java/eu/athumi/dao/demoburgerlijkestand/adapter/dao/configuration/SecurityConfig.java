package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    TokenManager tokenManager(@Value("${spring.security.oauth2.registration.name}") String clientRegistrationName,
                              @Value("${spring.security.oauth2.client.registration.demo.client-id}") String clientId,
                              ClientRegistrationRepository clientRegistrationRepository,
                              OAuth2AuthorizedClientService authorizedClientService) {
        return new TokenManager(clientRegistrationName, clientId, authorizedClientManager(clientRegistrationRepository, authorizedClientService));
    }

    private AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientService authorizedClientService) {
        AuthorizedClientServiceOAuth2AuthorizedClientManager clientManager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientService);
        clientManager.setAuthorizedClientProvider(OAuth2AuthorizedClientProviderBuilder.builder()
                .clientCredentials()
                .build());
        return clientManager;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(custom -> custom.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Client(Customizer.withDefaults())
                .build();
    }

}