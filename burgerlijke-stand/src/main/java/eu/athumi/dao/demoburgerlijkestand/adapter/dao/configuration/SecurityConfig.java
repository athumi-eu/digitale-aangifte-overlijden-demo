package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.endpoint.NimbusJwtClientAuthenticationParametersConverter;
import org.springframework.security.oauth2.client.endpoint.RestClientClientCredentialsTokenResponseClient;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private ClientConfigurationProperties clientConfigurationProperties;

    public SecurityConfig(ClientConfigurationProperties clientConfigurationProperties) {
        this.clientConfigurationProperties = clientConfigurationProperties;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated()
            )
            .build();
    }

    @Bean
    @Profile("beta")
    public RestClientClientCredentialsTokenResponseClient authManager() {
        RestClientClientCredentialsTokenResponseClient client =
                new RestClientClientCredentialsTokenResponseClient();
        client.addParametersConverter(
                new NimbusJwtClientAuthenticationParametersConverter<>(
                        item -> clientConfigurationProperties.getKey(item.getClientName())
                )
        );
        return client;
    }
}
