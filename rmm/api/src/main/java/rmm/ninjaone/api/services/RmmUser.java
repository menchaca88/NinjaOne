package rmm.ninjaone.api.services;

import java.util.UUID;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class RmmUser extends User {
    private final UUID userId;

    public RmmUser(UUID userId, String username, String password, String role) {
        super(username, password, AuthorityUtils.createAuthorityList(role));
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }
}