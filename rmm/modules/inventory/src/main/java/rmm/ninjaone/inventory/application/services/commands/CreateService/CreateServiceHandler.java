package rmm.ninjaone.inventory.application.services.commands.CreateService;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.inventory.domain.contracts.services.ServiceSrv;

@Component("CreateServiceHandler")
public class CreateServiceHandler extends BaseHandler<CreateServiceCommand, CreateServiceResult> {
    private ServiceSrv serviceSrv;

    public CreateServiceHandler(UserContext context, ServiceSrv serviceSrv) {
        super(context);
        this.serviceSrv = serviceSrv;
    }

    @Override
    public CreateServiceResult handle(CreateServiceCommand command) {
        var service = serviceSrv.create(context.getUserId(), command.getTypeId());

        var result = new CreateServiceResult();
        result.setId(service.getId());
        result.setName(service.getTypeName());

        return result;
    }
}
