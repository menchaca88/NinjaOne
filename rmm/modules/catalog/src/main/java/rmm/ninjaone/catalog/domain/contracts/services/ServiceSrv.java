package rmm.ninjaone.catalog.domain.contracts.services;

import java.util.List;
import java.util.UUID;

import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;
import rmm.ninjaone.catalog.domain.models.services.ServiceType;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.ServiceSubscription;

public interface ServiceSrv {
    ServiceType get(UUID id);
    ServiceType get(Sku sku);
    List<ServiceType> getAll();
    ServiceType create(String name, ServiceSubscription subscription);
    ServiceType update(UUID id, String name);
    ServiceType delete(UUID id);
}
