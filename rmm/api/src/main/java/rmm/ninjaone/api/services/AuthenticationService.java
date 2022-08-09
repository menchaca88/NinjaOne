package rmm.ninjaone.api.services;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.api.support.TemporalStorage;
import rmm.ninjaone.api.support.jwt.JwtDetails;
import rmm.ninjaone.api.support.jwt.JwtProvider;

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

    public UUID register(String name, String email, String password) {
        var encodedPassword = passwordEncoder.encode(password);

        //TODO: call use case
        return storage.Add(name, email, encodedPassword).id;
    }
}
