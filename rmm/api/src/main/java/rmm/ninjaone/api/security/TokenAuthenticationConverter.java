package rmm.ninjaone.api.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.api.support.jwt.JwtProvider;

@RequiredArgsConstructor
public class TokenAuthenticationConverter implements AuthenticationConverter {
    private final SecurityProperties properties;
    private final JwtProvider jwtProvider;

    @Override
    public Authentication convert(HttpServletRequest request) {
        var authHeader = request.getHeader(properties.getAuthHeader());

        if (authHeader == null || !StringUtils.startsWithIgnoreCase(authHeader, properties.getHeaderPrefix()))
            return null;

        return getAuthenticationFromRequest(authHeader);
    }

    private Authentication getAuthenticationFromRequest(String bearerToken) {
        String token = bearerToken.substring(properties.getHeaderPrefix().length() + 1);

        var authentication = jwtProvider.decodeToken(token);

        return authentication;
    }
}
