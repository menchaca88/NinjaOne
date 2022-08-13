package rmm.ninjaone.inventory.application.services.queries.ListServices;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.inventory.domain.contracts.services.ServiceSrv;

@Component("ListServicesHandler")
public class ListServicesHandler extends BaseHandler<ListServicesQuery, ListServicesResult> {
    private ServiceSrv serviceSrv;

    public ListServicesHandler(UserContext context, ServiceSrv serviceSrv) {
        super(context);
        this.serviceSrv = serviceSrv;
    }

    @Override
    public ListServicesResult handle(ListServicesQuery command) {
        var services = serviceSrv.getAll(context.getUserId());

        var result = new ListServicesResult();
        result.setServices(services
            .stream()
            .map(s -> { 
                var service = new ListServicesItem();
                service.setId(s.getId());
                service.setName(s.getTypeName());

                return service;
            })
            .toList());

        return result;
    }
}
