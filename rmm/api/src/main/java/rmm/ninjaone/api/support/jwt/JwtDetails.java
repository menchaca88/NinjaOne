package rmm.ninjaone.api.support.jwt;

import java.util.Date;

import lombok.Data;

@Data
public class JwtDetails {
    private String accessToken;
    private Date expiresAt;
}