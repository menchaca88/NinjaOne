package rmm.ninjaone.api.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.api.support.TemporalStorage;

@Service
@RequiredArgsConstructor
public class RmmUserDetailsService implements UserDetailsService {
    private final TemporalStorage storage;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO: Fetch from use case

        var user = storage.findByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException(username);

        var rmmUser = new RmmUser(user.id, user.email, user.password);
        return rmmUser;
    }
}
