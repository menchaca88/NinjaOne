package rmm.ninjaone.catalog.application.devices.queries.DeviceDetails;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;
import rmm.ninjaone.catalog.domain.contracts.devices.DeviceSrv;
import rmm.ninjaone.catalog.domain.models.devices.DeviceType;

@Component("DeviceTypeDetailsHandler")
public class DeviceDetailsHandler extends BaseHandler<DeviceDetailsQuery, DeviceDetailsResult> {
    private final DeviceSrv deviceSrv;

    public DeviceDetailsHandler(UserContext context, DeviceSrv deviceSrv) {
        super(context);
        this.deviceSrv = deviceSrv;
    }

    @Override
    public DeviceDetailsResult handle(DeviceDetailsQuery command) {
        DeviceType device = null;
        
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
