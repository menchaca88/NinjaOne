package rmm.ninjaone.api.services;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.api.support.jwt.JwtDetails;
import rmm.ninjaone.api.support.jwt.JwtProvider;
import rmm.ninjaone.identity.application.commands.CreateUser.CreateUserCommand;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final Pipeline pipeline;

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public JwtDetails login(String username, String password) {
        var credentials = new UsernamePasswordAuthenticationToken(username, password);
        var authentication = authenticationManager.authenticate(credentials);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.createToken(authentication);
    }

    public UUID register(String name, String role, String email, String password) {
        var command = new CreateUserCommand();
        command.setName(name);
        command.setEmail(email);
        command.setPassword(password);
        command.setRole(role);
        
        var result = pipeline.send(command);
        return result.getId();
    }
}
