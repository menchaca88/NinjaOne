package rmm.ninjaone.inventory.application.services.commands.RenameType;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.inventory.domain.contracts.services.ServiceSrv;

@Component("RenameServiceTypeHandler")
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
