package rmm.ninjaone.api.support.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.api.security.SecurityProperties;
import rmm.ninjaone.api.services.RmmUser;

import java.util.Date;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
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
            .withClaim(properties.getUserClaim(), user.getUserId().toString())
            .withArrayClaim(properties.getRolesClaim(), user
                .getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .toArray(String[]::new))
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
        var userId = UUID.fromString(jwt.getClaim(properties.getUserClaim()).asString());
        var authorities = jwt
        .getClaim(properties.getRolesClaim())
        .asArray(String.class);

        var authentication = new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.createAuthorityList(authorities));
        authentication.setDetails(userId);

        return authentication;
    }
}
