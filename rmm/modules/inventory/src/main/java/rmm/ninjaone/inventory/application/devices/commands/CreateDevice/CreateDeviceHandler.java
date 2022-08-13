package rmm.ninjaone.inventory.application.devices.commands.CreateDevice;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.inventory.domain.contracts.devices.DeviceSrv;

@Component("CreateDeviceHandler")
public class CreateDeviceHandler extends BaseHandler<CreateDeviceCommand, CreateDeviceResult> {
    private DeviceSrv deviceSrv;

    public CreateDeviceHandler(UserContext context, DeviceSrv deviceSrv) {
        super(context);
        this.deviceSrv = deviceSrv;
    }

    @Override
    public CreateDeviceResult handle(CreateDeviceCommand command) {
        var device = deviceSrv.create(context.getUserId(), command.getCount(), command.getTypeId());

        var result = new CreateDeviceResult();
        result.setId(device.getId());
        result.setCount(device.getCount());
        result.setName(device.getTypeName());

        return result;
    }
}
