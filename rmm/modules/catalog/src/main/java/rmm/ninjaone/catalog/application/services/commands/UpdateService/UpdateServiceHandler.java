package rmm.ninjaone.catalog.application.services.commands.UpdateService;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.catalog.domain.contracts.services.ServiceSrv;

@Component
public class UpdateServiceHandler extends BaseHandler<UpdateServiceCommand, UpdateServiceResult> {
    private final ServiceSrv serviceSrv;

    public UpdateServiceHandler(UserContext context, ServiceSrv serviceSrv) {
        super(context);
        this.serviceSrv = serviceSrv;
    }

    @Override
    public UpdateServiceResult handle(UpdateServiceCommand command) {
        var service = serviceSrv.update(command.getId(), command.getName());

        var result = new UpdateServiceResult();
        result.setId(service.getId());
        result.setName(service.getName());
        result.setSku(service.getSku().getRaw());
        result.setSubscription(service.getSubscription().getName());
            
        return result;
    }
}
