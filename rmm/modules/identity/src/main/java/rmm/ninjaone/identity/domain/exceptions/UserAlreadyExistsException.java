package rmm.ninjaone.identity.domain.exceptions;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.exceptions.DomainException;

@Getter
public class UserAlreadyExistsException extends DomainException {
    private final String email;
    
    public UserAlreadyExistsException(String email) {
        this.email = email;
    }
}
