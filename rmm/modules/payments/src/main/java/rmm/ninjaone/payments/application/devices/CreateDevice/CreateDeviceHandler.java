package rmm.ninjaone.payments.application.devices.CreateDevice;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.payments.domain.contracts.devices.DeviceSrv;

@Component("CreateDeviceChargeHandler")
public class CreateDeviceHandler extends BaseHandler<CreateDeviceCommand, CreateDeviceResult> {
    private DeviceSrv deviceSrv;

    public CreateDeviceHandler(UserContext context, DeviceSrv deviceSrv) {
        super(context);
        this.deviceSrv = deviceSrv;
    }

    @Override
    public CreateDeviceResult handle(CreateDeviceCommand command) {
        var device = deviceSrv.create(command.getId(), command.getPayerId(), command.getCount(), command.getTypeId(), command.getTypeName());

        var result = new CreateDeviceResult();
        result.setId(device.getId());
        result.setCount(device.getCount());
        result.setName(device.getTypeName());

        return result;
    }
}
