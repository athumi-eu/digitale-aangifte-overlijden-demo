package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

public class TokenManager {
    private final String clientRegistrationName;
    private final String clientId;
    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public TokenManager(String clientRegistrationName, String clientId, OAuth2AuthorizedClientManager authorizedClientManager) {
        this.clientRegistrationName = clientRegistrationName;
        this.clientId = clientId;
        this.authorizedClientManager = authorizedClientManager;
    }

    public String getAccessToken() {
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId(this.clientRegistrationName).principal(this.clientId).build();
        OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        System.out.println("#########");
        System.out.println(accessToken.getTokenValue());
        System.out.println("#########");
        return accessToken.getTokenValue();
    }
}
