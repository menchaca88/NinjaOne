package rmm.ninjaone.api.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.api.security.SecurityProperties;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtProvider {
    private final SecurityProperties properties;

    public JwtDetails createToken(Authentication authentication) {
        var user = (RmmUser)authentication.getPrincipal();

        Date expiresAt = new Date(System.currentTimeMillis() + properties.getExpirationDate());
        byte[] secret = properties.getSecret().getBytes();

        String token = JWT
            .create()
            .withSubject(user.getUsername())
            .withExpiresAt(expiresAt)
            .withClaim(properties.getUserClaim(), user.getUserId())
            .sign(Algorithm.HMAC512(secret));

        var details = new JwtDetails();
        details.setAccessToken(token);
        details.setExpiresAt(expiresAt);

        return details;
    }

    public Authentication decodeToken(String accessToken) {
        byte[] secret = properties.getSecret().getBytes();

        var jwt = JWT
            .require(Algorithm.HMAC512(secret))
            .build()
            .verify(accessToken);

        var email = jwt.getSubject();
        var userId = jwt.getClaim(properties.getUserClaim()).asLong();

        var authentication = new UsernamePasswordAuthenticationToken(email, null);
        authentication.setDetails(userId);

        return authentication;
    }
}