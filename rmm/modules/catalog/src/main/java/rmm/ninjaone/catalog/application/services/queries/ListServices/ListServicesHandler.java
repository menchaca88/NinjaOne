package rmm.ninjaone.catalog.application.services.queries.ListServices;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.catalog.domain.contracts.services.ServiceSrv;

@Component
public class ListServicesHandler extends BaseHandler<ListServicesQuery, ListServicesResult> {
    private final ServiceSrv serviceSrv;

    public ListServicesHandler(UserContext context, ServiceSrv serviceSrv) {
        super(context);
        this.serviceSrv = serviceSrv;
    }

    @Override
    public ListServicesResult handle(ListServicesQuery command) {
        var services = serviceSrv.getAll();

        var items = services
            .stream()
            .map(u -> {
                var service = new ListServicesItem();
                service.setId(u.getId());
                service.setName(u.getName());
                service.setSku(u.getSku().getRaw());
                service.setSubscription(u.getSubscription().getName());

                return service;
            })
            .toList();

        var result = new ListServicesResult();
        result.setServices(items);

        return result;
    }
}
