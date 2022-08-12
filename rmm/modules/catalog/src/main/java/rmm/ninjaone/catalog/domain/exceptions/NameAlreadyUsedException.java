package rmm.ninjaone.catalog.domain.exceptions;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.exceptions.DomainException;

@Getter
public class NameAlreadyUsedException extends DomainException {
    private final String name;
    
    public NameAlreadyUsedException(String name) {
        this.name = name;
    }
}
