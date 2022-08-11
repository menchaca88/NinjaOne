package rmm.ninjaone.api.support.setup;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiUrls {
    private String prefix;
    private String users;
    private String catalog;
    private String docs;
    private String login;
    private String register;
}