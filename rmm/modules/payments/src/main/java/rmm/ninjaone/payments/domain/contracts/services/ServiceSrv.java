package rmm.ninjaone.payments.domain.contracts.services;

import java.util.UUID;

import rmm.ninjaone.payments.domain.models.ServiceCharge;

public interface ServiceSrv {
    ServiceCharge create(UUID id, UUID payerId, UUID typeId, String typeName);
    ServiceCharge delete(UUID id);
    int deleteType(UUID typeId);
    int renameType(UUID typeId, String name);
}
