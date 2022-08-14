package rmm.ninjaone.api.support.setup;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiUrls {
    private String prefix;
    private String login;
    private String register;
    private String users;
    private String catalog;
    private String inventory;
    private String payments;
    private String invoices;
    private List<String> docs;
}