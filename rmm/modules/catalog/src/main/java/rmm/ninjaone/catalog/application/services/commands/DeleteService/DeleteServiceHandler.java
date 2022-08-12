package rmm.ninjaone.catalog.application.services.commands.DeleteService;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.catalog.domain.contracts.services.ServiceSrv;

@Component
public class DeleteServiceHandler extends BaseHandler<DeleteServiceCommand, DeleteServiceResult> {
    private final ServiceSrv serviceSrv;

    public DeleteServiceHandler(UserContext context, ServiceSrv serviceSrv) {
        super(context);
        this.serviceSrv = serviceSrv;
    }

    @Override
    public DeleteServiceResult handle(DeleteServiceCommand command) {
        var service = serviceSrv.delete(command.getId());

        var result = new DeleteServiceResult();
        result.setId(service.getId());
        result.setName(service.getName());
        result.setSku(service.getSku().getRaw());
        result.setSubscription(service.getSubscription().getName());
            
        return result;
    }
}
