package rmm.ninjaone.api.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.identity.application.queries.UserDetails.UserDetailsQuery;
import rmm.ninjaone.identity.domain.exceptions.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class RmmUserDetailsService implements UserDetailsService {
    private final Pipeline pipeline;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var query = new UserDetailsQuery();
        query.setEmail(username);

        try {
            var result = pipeline.send(query);
            var rmmUser = new RmmUser(
                result.getId(),
                result.getEmail(),
                result.getPassword(),
                result.getRole());

            return rmmUser;
        } catch (UserNotFoundException ex) {
            throw new UsernameNotFoundException(username);
        }
    }
}
