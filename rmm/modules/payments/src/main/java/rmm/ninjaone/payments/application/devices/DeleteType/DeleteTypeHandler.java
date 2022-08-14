package rmm.ninjaone.payments.application.devices.DeleteType;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.payments.domain.contracts.devices.DeviceSrv;

@Component("DeleteTypeDeviceChargeHandler")
public class DeleteTypeHandler extends BaseHandler<DeleteTypeCommand, DeleteTypeResult> {
    private DeviceSrv deviceSrv;

    public DeleteTypeHandler(UserContext context, DeviceSrv deviceSrv) {
        super(context);
        this.deviceSrv = deviceSrv;
    }

    @Override
    public DeleteTypeResult handle(DeleteTypeCommand command) {
        var count = deviceSrv.deleteType(command.getTypeId());

        var result = new DeleteTypeResult();
        result.setTypeId(command.getTypeId());
        result.setCount(count);

        return result;
    }
}
