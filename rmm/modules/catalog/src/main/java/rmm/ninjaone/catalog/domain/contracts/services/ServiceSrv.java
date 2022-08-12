package rmm.ninjaone.catalog.domain.contracts.services;

import java.util.List;
import java.util.UUID;

import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;
import rmm.ninjaone.catalog.domain.models.services.Service;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.ServiceSubscription;

public interface ServiceSrv {
    Service get(UUID id);
    Service get(Sku sku);
    List<Service> getAll();
    Service create(String name, ServiceSubscription subscription);
    Service update(UUID id, String name);
    Service delete(UUID id);
}
