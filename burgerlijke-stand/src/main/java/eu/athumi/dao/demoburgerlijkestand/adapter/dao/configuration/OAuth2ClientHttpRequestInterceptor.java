/*
 * Copyright 2002-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This class will be added to SPRING 6.X.X but isn't available yet ZUCHT
 */

package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Steve Riesenberg
 */
public final class OAuth2ClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    // @formatter:off
    private static final Map<HttpStatusCode, String> OAUTH2_ERROR_CODES = Map.of(
            HttpStatus.UNAUTHORIZED, OAuth2ErrorCodes.INVALID_TOKEN,
            HttpStatus.FORBIDDEN, OAuth2ErrorCodes.INSUFFICIENT_SCOPE
    );
    // @formatter:on

    private static final Authentication ANONYMOUS_AUTHENTICATION = new AnonymousAuthenticationToken(
        "anonymous",
        "anonymousUser",
        AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS")
    );

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    private final String clientRegistrationId;

    // @formatter:off
    private OAuth2AuthorizationFailureHandler authorizationFailureHandler =
            (clientRegistrationId, principal, attributes) -> {};
    // @formatter:on

    private SecurityContextHolderStrategy securityContextHolderStrategy =
        SecurityContextHolder.getContextHolderStrategy();

    private Supplier<Authentication> authentication = () ->
        this.securityContextHolderStrategy.getContext().getAuthentication();

    public OAuth2ClientHttpRequestInterceptor(
        OAuth2AuthorizedClientManager authorizedClientManager,
        String clientRegistrationId
    ) {
        Assert.notNull(authorizedClientManager, "authorizedClientManager cannot be null");
        Assert.hasText(clientRegistrationId, "clientRegistrationId cannot be empty");
        this.authorizedClientManager = authorizedClientManager;
        this.clientRegistrationId = clientRegistrationId;
    }

    private static void removeAuthorizedClient(
        OAuth2AuthorizedClientRepository authorizedClientRepository,
        String clientRegistrationId,
        Authentication principal,
        Map<String, Object> attributes
    ) {
        HttpServletRequest request = (HttpServletRequest) attributes.get(
            HttpServletRequest.class.getName()
        );
        HttpServletResponse response = (HttpServletResponse) attributes.get(
            HttpServletResponse.class.getName()
        );
        authorizedClientRepository.removeAuthorizedClient(
            clientRegistrationId,
            principal,
            request,
            response
        );
    }

    private static void removeAuthorizedClient(
        OAuth2AuthorizedClientService authorizedClientService,
        String clientRegistrationId,
        Authentication principal
    ) {
        authorizedClientService.removeAuthorizedClient(clientRegistrationId, principal.getName());
    }

    private static OAuth2Error resolveOAuth2ErrorIfPossible(
        HttpHeaders headers,
        HttpStatusCode httpStatus
    ) {
        String wwwAuthenticateHeader = headers.getFirst(HttpHeaders.WWW_AUTHENTICATE);
        if (wwwAuthenticateHeader == null) {
            return null;
        }

        Map<String, String> parameters = parseWwwAuthenticateHeader(wwwAuthenticateHeader);
        if (parameters.containsKey(OAuth2ParameterNames.ERROR)) {
            return new OAuth2Error(
                parameters.get(OAuth2ParameterNames.ERROR),
                parameters.get(OAuth2ParameterNames.ERROR_DESCRIPTION),
                parameters.get(OAuth2ParameterNames.ERROR_URI)
            );
        }

        String errorCode = OAUTH2_ERROR_CODES.get(httpStatus);
        if (errorCode != null) {
            return new OAuth2Error(
                errorCode,
                null,
                "https://tools.ietf.org/html/rfc6750#section-3.1"
            );
        }

        return null;
    }

    private static Map<String, String> parseWwwAuthenticateHeader(String wwwAuthenticateHeader) {
        if (
            !StringUtils.hasLength(wwwAuthenticateHeader) ||
                !StringUtils.startsWithIgnoreCase(wwwAuthenticateHeader, "bearer")
        ) {
            return Map.of();
        }

        String headerValue = wwwAuthenticateHeader.substring("bearer".length()).stripLeading();
        Map<String, String> parameters = new HashMap<>();
        for (String kvPair : StringUtils.delimitedListToStringArray(headerValue, ",")) {
            String[] kv = StringUtils.split(kvPair, "=");
            if (kv == null || kv.length <= 1) {
                continue;
            }

            parameters.put(kv[0].trim(), kv[1].trim().replace("\"", ""));
        }

        return parameters;
    }

    private static Authentication createAuthentication(final String principalName) {
        Assert.hasText(principalName, "principalName cannot be empty");
        return new AbstractAuthenticationToken(null) {
            @Override
            public Object getPrincipal() {
                return principalName;
            }

            @Override
            public Object getCredentials() {
                return "";
            }
        };
    }

    public void setAuthorizationFailureHandler(
        OAuth2AuthorizationFailureHandler authorizationFailureHandler
    ) {
        Assert.notNull(authorizationFailureHandler, "authorizationFailureHandler cannot be null");
        this.authorizationFailureHandler = authorizationFailureHandler;
    }

    public void setAuthorizedClientRepository(
        OAuth2AuthorizedClientRepository authorizedClientRepository
    ) {
        Assert.notNull(authorizedClientRepository, "authorizedClientRepository cannot be null");
        this.authorizationFailureHandler =
            new RemoveAuthorizedClientOAuth2AuthorizationFailureHandler(
                (clientRegistrationId, principal, attributes) ->
                    removeAuthorizedClient(
                        authorizedClientRepository,
                        clientRegistrationId,
                        principal,
                        attributes
                    )
            );
    }

    public void setAuthorizedClientService(OAuth2AuthorizedClientService authorizedClientService) {
        Assert.notNull(authorizedClientService, "authorizedClientService cannot be null");
        this.authorizationFailureHandler =
            new RemoveAuthorizedClientOAuth2AuthorizationFailureHandler(
                (clientRegistrationId, principal, attributes) ->
                    removeAuthorizedClient(authorizedClientService, clientRegistrationId, principal)
            );
    }

    public void setSecurityContextHolderStrategy(
        SecurityContextHolderStrategy securityContextHolderStrategy
    ) {
        Assert.notNull(
            securityContextHolderStrategy,
            "securityContextHolderStrategy cannot be null"
        );
        this.securityContextHolderStrategy = securityContextHolderStrategy;
    }

    public void setPrincipalName(String principalName) {
        Assert.hasText(principalName, "principalName cannot be empty");
        Authentication principal = createAuthentication(principalName);
        this.authentication = () -> principal;
    }

    public void setPrincipal(Authentication principal) {
        Assert.notNull(principal, "principal cannot be null");
        this.authentication = () -> principal;
    }

    public Consumer<ClientHttpRequest> httpRequest() {
        return this::authorizeClient;
    }

    public ResponseErrorHandler errorHandler() {
        return new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                handleAuthorizationFailure(response.getHeaders(), response.getStatusCode());
                super.handleError(response);
            }
        };
    }

    @Override
    public ClientHttpResponse intercept(
        HttpRequest request,
        byte[] body,
        ClientHttpRequestExecution execution
    ) throws IOException {
        authorizeClient(request);
        try {
            ClientHttpResponse response = execution.execute(request, body);
            handleAuthorizationFailure(response.getHeaders(), response.getStatusCode());
            return response;
        } catch (RestClientResponseException ex) {
            handleAuthorizationFailure(ex.getResponseHeaders(), ex.getStatusCode());
            throw ex;
        } catch (OAuth2AuthorizationException ex) {
            handleAuthorizationFailure(ex);
            throw ex;
        }
    }

    private void authorizeClient(HttpRequest request) {
        Authentication principal = this.authentication.get();
        if (principal == null) {
            principal = ANONYMOUS_AUTHENTICATION;
        }
        // @formatter:off
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId(this.clientRegistrationId)
                .principal(principal)
                .build();
        // @formatter:on
        OAuth2AuthorizedClient authorizedClient =
            this.authorizedClientManager.authorize(authorizeRequest);
        if (authorizedClient != null) {
            request.getHeaders().setBearerAuth(authorizedClient.getAccessToken().getTokenValue());
        }
    }

    private void handleAuthorizationFailure(HttpHeaders headers, HttpStatusCode httpStatus) {
        OAuth2Error error = resolveOAuth2ErrorIfPossible(headers, httpStatus);
        if (error == null) {
            return;
        }

        ClientAuthorizationException authorizationException = new ClientAuthorizationException(
            error,
            this.clientRegistrationId
        );
        handleAuthorizationFailure(authorizationException);
    }

    private void handleAuthorizationFailure(OAuth2AuthorizationException authorizationException) {
        Authentication principal = this.authentication.get();
        if (principal == null) {
            principal = ANONYMOUS_AUTHENTICATION;
        }

        ServletRequestAttributes requestAttributes =
            (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Map<String, Object> attributes = new HashMap<>();
        if (requestAttributes != null) {
            attributes.put(HttpServletRequest.class.getName(), requestAttributes.getRequest());
            if (requestAttributes.getResponse() != null) {
                attributes.put(
                    HttpServletResponse.class.getName(),
                    requestAttributes.getResponse()
                );
            }
        }

        this.authorizationFailureHandler.onAuthorizationFailure(
            authorizationException,
            principal,
            attributes
        );
    }
}
