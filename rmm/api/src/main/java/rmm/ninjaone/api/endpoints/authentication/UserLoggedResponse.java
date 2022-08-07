package rmm.ninjaone.api.endpoints.authentication;

import java.util.Date;

import lombok.Data;

@Data
public class UserLoggedResponse {
    private String accessToken;
    private Date expiresAt;
}