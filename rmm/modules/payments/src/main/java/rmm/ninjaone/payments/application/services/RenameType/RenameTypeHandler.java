package rmm.ninjaone.payments.application.services.RenameType;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.payments.domain.contracts.services.ServiceSrv;

@Component("RenameServiceTypeChargeHandler")
public class RenameTypeHandler extends BaseHandler<RenameTypeCommand, RenameTypeResult> {
    private ServiceSrv serviceSrv;

    public RenameTypeHandler(UserContext context, ServiceSrv serviceSrv) {
        super(context);
        this.serviceSrv = serviceSrv;
    }

    @Override
    public RenameTypeResult handle(RenameTypeCommand command) {
        var count = serviceSrv.renameType(command.getTypeId(), command.getName());

        var result = new RenameTypeResult();
        result.setTypeId(command.getTypeId());
        result.setName(command.getName());
        result.setCount(count);

        return result;
    }
}
