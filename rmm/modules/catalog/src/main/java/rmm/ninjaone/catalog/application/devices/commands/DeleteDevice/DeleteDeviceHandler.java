package rmm.ninjaone.catalog.application.devices.commands.DeleteDevice;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.catalog.domain.contracts.devices.DeviceSrv;

@Component
public class DeleteDeviceHandler extends BaseHandler<DeleteDeviceCommand, DeleteDeviceResult> {
    private final DeviceSrv deviceSrv;

    public DeleteDeviceHandler(UserContext context, DeviceSrv deviceSrv) {
        super(context);
        this.deviceSrv = deviceSrv;
    }

    @Override
    public DeleteDeviceResult handle(DeleteDeviceCommand command) {
        var device = deviceSrv.delete(command.getId());

        var result = new DeleteDeviceResult();
        result.setId(device.getId());
        result.setName(device.getName());
        result.setSku(device.getSku().getRaw());
        result.setSubscription(device.getSubscription().getName());
            
        return result;
    }
}
