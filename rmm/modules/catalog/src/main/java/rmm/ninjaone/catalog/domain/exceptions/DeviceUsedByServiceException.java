package rmm.ninjaone.catalog.domain.exceptions;

import java.util.UUID;

import lombok.Getter;
import rmm.ninjaone.buildingblocks.domain.exceptions.DomainException;

@Getter
public class DeviceUsedByServiceException extends DomainException {
    private final UUID deviceId;
    
    public DeviceUsedByServiceException(UUID deviceId) {
        this.deviceId = deviceId;
    }
}
