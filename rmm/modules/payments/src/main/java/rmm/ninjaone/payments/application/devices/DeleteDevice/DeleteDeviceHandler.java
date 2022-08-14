package rmm.ninjaone.payments.application.devices.DeleteDevice;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.payments.domain.contracts.devices.DeviceSrv;

@Component("DeleteDeviceChargeHandler")
public class DeleteDeviceHandler extends BaseHandler<DeleteDeviceCommand, DeleteDeviceResult> {
    private DeviceSrv deviceSrv;

    public DeleteDeviceHandler(UserContext context, DeviceSrv deviceSrv) {
        super(context);
        this.deviceSrv = deviceSrv;
    }

    @Override
    public DeleteDeviceResult handle(DeleteDeviceCommand command) {
        var device = deviceSrv.delete(command.getId());

        var result = new DeleteDeviceResult();
        result.setId(device.getId());
        result.setCount(device.getCount());
        result.setName(device.getTypeName());

        return result;
    }
}
