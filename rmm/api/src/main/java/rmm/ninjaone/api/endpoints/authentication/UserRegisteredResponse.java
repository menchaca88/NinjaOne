package rmm.ninjaone.api.endpoints.authentication;

import java.util.UUID;

import lombok.Data;

@Data
public class UserRegisteredResponse {
    private UUID userId;
    private String userEmail;
}
