package rmm.ninjaone.inventory.application.devices.commands.RenameType;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.inventory.domain.contracts.devices.DeviceSrv;

@Component("RenameDeviceTypeHandler")
public class RenameTypeHandler extends BaseHandler<RenameTypeCommand, RenameTypeResult> {
    private DeviceSrv deviceSrv;

    public RenameTypeHandler(UserContext context, DeviceSrv deviceSrv) {
        super(context);
        this.deviceSrv = deviceSrv;
    }

    @Override
    public RenameTypeResult handle(RenameTypeCommand command) {
        var count = deviceSrv.renameType(command.getTypeId(), command.getName());

        var result = new RenameTypeResult();
        result.setTypeId(command.getTypeId());
        result.setName(command.getName());
        result.setCount(count);

        return result;
    }
}
