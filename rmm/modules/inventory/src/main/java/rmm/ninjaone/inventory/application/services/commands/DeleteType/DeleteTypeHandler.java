package rmm.ninjaone.inventory.application.services.commands.DeleteType;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.inventory.domain.contracts.services.ServiceSrv;

@Component("DeleteTypeServiceHandler")
public class DeleteTypeHandler extends BaseHandler<DeleteTypeCommand, DeleteTypeResult> {
    private ServiceSrv serviceSrv;

    public DeleteTypeHandler(UserContext context, ServiceSrv serviceSrv) {
        super(context);
        this.serviceSrv = serviceSrv;
    }

    @Override
    public DeleteTypeResult handle(DeleteTypeCommand command) {
        var count = serviceSrv.deleteType(command.getTypeId());

        var result = new DeleteTypeResult();
        result.setTypeId(command.getTypeId());
        result.setCount(count);

        return result;
    }
}
