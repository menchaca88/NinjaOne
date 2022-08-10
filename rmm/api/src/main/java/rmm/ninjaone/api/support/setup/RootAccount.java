package rmm.ninjaone.api.support.setup;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "root")
public class RootAccount {
    private String name;
    private String email;
    private String password;
}
