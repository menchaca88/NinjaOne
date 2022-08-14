package rmm.ninjaone.catalog.port;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.ports.catalog.CatalogPort;
import rmm.ninjaone.buildingblocks.ports.catalog.Details;
import rmm.ninjaone.buildingblocks.ports.catalog.SubscriptionData;
import rmm.ninjaone.catalog.domain.contracts.devices.DeviceSrv;
import rmm.ninjaone.catalog.domain.contracts.services.ServiceSrv;
import rmm.ninjaone.catalog.domain.contracts.subscriptions.SubscriptionSrv;
import rmm.ninjaone.catalog.domain.exceptions.DeviceNotFoundException;
import rmm.ninjaone.catalog.domain.exceptions.ServiceNotFoundException;

@Service
@RequiredArgsConstructor
public class CatalogPortImpl implements CatalogPort {
    private final SubscriptionSrv subscriptionSrv;
    private final DeviceSrv deviceSrv;
    private final ServiceSrv ServiceSrv;

    @Override
    public Details findDevice(UUID id) {
        try {
            var device = deviceSrv.get(id);

            var rawData = subscriptionSrv.toData(device.getSubscription());
            var data = new SubscriptionData(rawData.getType(), rawData.getData());

            return new Details(device.getId(), device.getName(), device.getSku().getRaw(), data);
        }
        catch (DeviceNotFoundException ex) {
            return null;
        }
    }

    @Override
    public Details findService(UUID id) {
        try {
            var service = ServiceSrv.get(id);

            var rawData = subscriptionSrv.toData(service.getSubscription());
            var data = new SubscriptionData(rawData.getType(), rawData.getData());

            return new Details(service.getId(), service.getName(), service.getSku().getRaw(), data);
        }
        catch (ServiceNotFoundException ex) {
            return null;
        }
    }
}
