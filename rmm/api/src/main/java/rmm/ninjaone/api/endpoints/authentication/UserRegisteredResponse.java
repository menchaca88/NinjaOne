package rmm.ninjaone.api.endpoints.authentication;

import lombok.Data;

@Data
public class UserRegisteredResponse {
    private Long userId;
    private String userEmail;
}
