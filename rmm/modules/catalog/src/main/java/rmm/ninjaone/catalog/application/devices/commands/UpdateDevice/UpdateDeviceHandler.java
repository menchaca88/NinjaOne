package rmm.ninjaone.catalog.application.devices.commands.UpdateDevice;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.catalog.domain.contracts.devices.DeviceSrv;

@Component
public class UpdateDeviceHandler extends BaseHandler<UpdateDeviceCommand, UpdateDeviceResult> {
    private final DeviceSrv deviceSrv;

    public UpdateDeviceHandler(UserContext context, DeviceSrv deviceSrv) {
        super(context);
        this.deviceSrv = deviceSrv;
    }

    @Override
    public UpdateDeviceResult handle(UpdateDeviceCommand command) {
        var device = deviceSrv.update(command.getId(), command.getName());

        var result = new UpdateDeviceResult();
        result.setId(device.getId());
        result.setName(device.getName());
        result.setSku(device.getSku().getRaw());
        result.setSubscription(device.getSubscription().getName());
            
        return result;
    }
}
