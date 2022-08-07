package rmm.ninjaone.api.security;

import org.springframework.context.MessageSource;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import rmm.ninjaone.api.services.JwtProvider;

@Component
public class TokenAuthenticationFilter extends AuthenticationFilter {

    public TokenAuthenticationFilter(MessageSource messageSource, SecurityProperties properties, JwtProvider jwtProvider) {
        super(new TokenAuthenticationManager(messageSource), new TokenAuthenticationConverter(properties, jwtProvider));

        var handler = new SimpleUrlAuthenticationSuccessHandler();
        handler.setRedirectStrategy(new NoRedirectStrategy());

        setSuccessHandler(handler);
    }
}
