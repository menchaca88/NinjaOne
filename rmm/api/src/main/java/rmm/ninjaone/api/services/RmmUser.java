package rmm.ninjaone.api.services;

import java.util.Collections;
import java.util.UUID;

import org.springframework.security.core.userdetails.User;

public class RmmUser extends User {
    private final UUID userId;

    public RmmUser(UUID userId, String username, String password) {
        super(username, password, Collections.emptyList());
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }
}