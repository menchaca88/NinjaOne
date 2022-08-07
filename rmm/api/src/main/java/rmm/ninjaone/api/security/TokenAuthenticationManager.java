package rmm.ninjaone.api.security;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenAuthenticationManager implements AuthenticationManager {
    private final MessageSource messageSource;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication == null)
            throw new BadCredentialsException(messageSource.getMessage("errors.credentials.invalid", null, Locale.getDefault()));
        
        var authenticated = new UsernamePasswordAuthenticationToken(
            authentication.getPrincipal(),
            authentication.getCredentials(),
            authentication.getAuthorities()
        );
        authenticated.setDetails(authentication.getDetails());

        return authenticated;
    }
}
