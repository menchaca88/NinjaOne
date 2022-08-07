package rmm.ninjaone.api.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final TemporalStorage storage;

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtDetails login(String username, String password) {
        var credentials = new UsernamePasswordAuthenticationToken(username, password);
        var authentication = authenticationManager.authenticate(credentials);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.createToken(authentication);
    }

    public long register(String name, String email, String password) {
        var encodedPassword = passwordEncoder.encode(password);

        //TODO: call use case
        return storage.Add(name, email, encodedPassword).id;
    }
}
