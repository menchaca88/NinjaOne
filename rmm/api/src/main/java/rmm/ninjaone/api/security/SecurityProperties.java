package rmm.ninjaone.api.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    private long expirationDate;
    private String userClaim;
    private String rolesClaim;
    private String secret;
    private String authHeader;
    private String headerPrefix;
    private String loginUrl;
    private String registerUrl;
}
