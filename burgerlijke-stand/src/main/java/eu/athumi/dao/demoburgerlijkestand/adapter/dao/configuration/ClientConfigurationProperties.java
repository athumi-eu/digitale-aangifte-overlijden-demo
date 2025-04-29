package eu.athumi.dao.demoburgerlijkestand.adapter.dao.configuration;

import com.nimbusds.jose.jwk.JWK;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.validation.annotation.Validated;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Validated
@ConfigurationProperties(prefix = "dao.gemeentes")
public class ClientConfigurationProperties {

    @NonNull
    private Map<String, GemeenteConfig> config;
    private String securityMethod;
    private String tokenUri;
    private String issuerUri;
    private String projectName;

    public ClientConfigurationProperties(String securityMethod, Map<String, GemeenteConfig> config, String tokenUri, String issuerUri, String projectName) {
        this.securityMethod = securityMethod;
        this.config = config;
        this.tokenUri = tokenUri;
        this.issuerUri = issuerUri;
        this.projectName = projectName;
    }

    public String getSecurityMethod() {
        return securityMethod;
    }

    public String getTokenUri() {
        return tokenUri;
    }

    public String getScope() {
        return "%s_lbbs".formatted(projectName);
    }

    @NonNull
    public Map<String, GemeenteConfig> getConfig() {
        return config;
    }

    public ClientConfigurationProperties setConfig(@NonNull Map<String, GemeenteConfig> config) {
        this.config = config;
        return this;
    }

    public List<GemeenteConfig> getGemeentes() {
        return new ArrayList<>(config.values());
    }

    public JWK getKey(String kbonummer) {
        return this.getGemeentes().stream()
                .filter(item -> item.kbonummer.equals(kbonummer))
                .findFirst()
                .map(GemeenteConfig::getJwk)
                .orElseThrow();
    }

    public String getIssuerUri() {
        return issuerUri;
    }

    public ClientAuthenticationMethod getAuthenticationMethod() {
        if("client_secret".equals(securityMethod)) {
            return ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
        }
        return ClientAuthenticationMethod.PRIVATE_KEY_JWT;
    }


    public static class GemeenteConfig {
        private final String naam;
        private final String kbonummer;
        private final String clientid;
        private final String clientsecret;
        private JWK jwk;

        public GemeenteConfig(String naam, String kbonummer, String clientid, String clientsecret) {
            this.naam = naam;
            this.kbonummer = kbonummer;
            this.clientid = clientid;
            this.clientsecret = clientsecret;
            try {
                this.jwk = JWK.parse(clientsecret);
            }catch ( ParseException ignored) {
            }
        }

        public String getNaam() {
            return naam;
        }

        public String getKbonummer() {
            return kbonummer;
        }

        public String getClientid() {
            return clientid;
        }

        public String getClientsecret() {
            return clientsecret;
        }

        public JWK getJwk() {
            return jwk;
        }
    }
}
