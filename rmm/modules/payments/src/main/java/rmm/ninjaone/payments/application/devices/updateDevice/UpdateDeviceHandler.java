package rmm.ninjaone.payments.application.devices.updateDevice;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.payments.domain.contracts.devices.DeviceSrv;

@Component("UpdateDeviceChargeHandler")
public class UpdateDeviceHandler extends BaseHandler<UpdateDeviceCommand, UpdateDeviceResult> {
    private DeviceSrv deviceSrv;

    public UpdateDeviceHandler(UserContext context, DeviceSrv deviceSrv) {
        super(context);
        this.deviceSrv = deviceSrv;
    }

    @Override
    public UpdateDeviceResult handle(UpdateDeviceCommand command) {
        var device = deviceSrv.update(command.getId(), command.getCount());

        var result = new UpdateDeviceResult();
        result.setId(device.getId());
        result.setCount(device.getCount());
        result.setName(device.getTypeName());

        return result;
    }
}
