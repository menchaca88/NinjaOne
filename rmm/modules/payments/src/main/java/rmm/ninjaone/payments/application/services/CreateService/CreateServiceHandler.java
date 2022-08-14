package rmm.ninjaone.payments.application.services.CreateService;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.payments.domain.contracts.services.ServiceSrv;

@Component("CreateServiceChargeHandler")
public class CreateServiceHandler extends BaseHandler<CreateServiceCommand, CreateServiceResult> {
    private ServiceSrv serviceSrv;

    public CreateServiceHandler(UserContext context, ServiceSrv serviceSrv) {
        super(context);
        this.serviceSrv = serviceSrv;
    }

    @Override
    public CreateServiceResult handle(CreateServiceCommand command) {
        var service = serviceSrv.create(command.getId(), command.getPayerId(), command.getTypeId(), command.getTypeName());

        var result = new CreateServiceResult();
        result.setId(service.getId());
        result.setName(service.getTypeName());

        return result;
    }
}
