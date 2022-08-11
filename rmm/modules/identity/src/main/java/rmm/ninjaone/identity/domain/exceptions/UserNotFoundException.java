package rmm.ninjaone.identity.domain.exceptions;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.exceptions.DomainException;

@Getter
public class UserNotFoundException extends DomainException {
    private final String email;
    private final UUID id;

    public UserNotFoundException(String email) {
        this.email = email;
        this.id = null;
    }

    public UserNotFoundException(UUID id) {
        this.email = null;
        this.id = id;
    }
}
