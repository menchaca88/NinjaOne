package rmm.ninjaone.catalog.application.services.queries.ServiceDetails;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;
import rmm.ninjaone.catalog.domain.contracts.services.ServiceSrv;
import rmm.ninjaone.catalog.domain.models.services.Service;

@Component
public class ServiceDetailsHandler extends BaseHandler<ServiceDetailsQuery, ServiceDetailsResult> {
    private final ServiceSrv serviceSrv;

    public ServiceDetailsHandler(UserContext context, ServiceSrv serviceSrv) {
        super(context);
        this.serviceSrv = serviceSrv;
    }

    @Override
    public ServiceDetailsResult handle(ServiceDetailsQuery command) {
        Service service = null;
        
        if (command.getId() != null)
            service = serviceSrv.get(command.getId());
        else if (command.getSku() != null)
            service = serviceSrv.get(Sku.of(command.getSku()));

        var result = new ServiceDetailsResult();

        result.setId(service.getId());
        result.setName(service.getName());
        result.setSku(service.getSku().getRaw());
        result.setSubscription(service.getSubscription().getName());

        return result;
    }
}
