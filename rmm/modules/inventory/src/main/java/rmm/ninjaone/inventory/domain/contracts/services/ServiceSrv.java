package rmm.ninjaone.inventory.domain.contracts.services;

import java.util.List;
import java.util.UUID;

import rmm.ninjaone.inventory.domain.models.Service;

public interface ServiceSrv {
    List<Service> getAll(UUID clientId);
    Service create(UUID clientId, UUID typeId);
    Service delete(UUID id);
    int deleteType(UUID typeId);
    int renameType(UUID typeId, String name);
}
