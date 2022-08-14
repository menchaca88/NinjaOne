package rmm.ninjaone.payments.application.services.DeleteService;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.payments.domain.contracts.services.ServiceSrv;

@Component("DeleteServiceChargeHandler")
public class DeleteServiceHandler extends BaseHandler<DeleteServiceCommand, DeleteServiceResult> {
    private ServiceSrv serviceSrv;

    public DeleteServiceHandler(UserContext context, ServiceSrv serviceSrv) {
        super(context);
        this.serviceSrv = serviceSrv;
    }

    @Override
    public DeleteServiceResult handle(DeleteServiceCommand command) {
        var service = serviceSrv.delete(command.getId());

        var result = new DeleteServiceResult();
        result.setId(service.getId());
        result.setName(service.getTypeName());

        return result;
    }
}
