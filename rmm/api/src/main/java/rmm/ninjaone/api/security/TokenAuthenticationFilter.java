package rmm.ninjaone.api.security;

import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import rmm.ninjaone.api.services.JwtProvider;

@Component
public class TokenAuthenticationFilter extends AuthenticationFilter {

    public TokenAuthenticationFilter(SecurityProperties properties, JwtProvider jwtProvider) {
        super(new TokenAuthenticationManager(), new TokenAuthenticationConverter(properties, jwtProvider));

        var handler = new SimpleUrlAuthenticationSuccessHandler();
        handler.setRedirectStrategy(new NoRedirectStrategy());

        setSuccessHandler(handler);
    }
}
