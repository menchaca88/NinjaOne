package rmm.ninjaone.catalog.application.devices.queries.DeviceDetails;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;
import rmm.ninjaone.catalog.domain.contracts.devices.DeviceSrv;
import rmm.ninjaone.catalog.domain.models.devices.Device;

@Component
@RequiredArgsConstructor
public class DeviceDetailsHandler implements Command.Handler<DeviceDetailsQuery, DeviceDetailsResult> {
    private final DeviceSrv deviceSrv;

    @Override
    public DeviceDetailsResult handle(DeviceDetailsQuery command) {
        Device device = null;
        
        if (command.getId() != null)
            device = deviceSrv.get(command.getId());
        else if (command.getSku() != null)
            device = deviceSrv.get(Sku.of(command.getSku()));

        var result = new DeviceDetailsResult();

        result.setId(device.getId());
        result.setName(device.getName());
        result.setSku(device.getSku().getRaw());
        result.setSubscription(device.getSubscription().getName());

        return result;
    }
}
