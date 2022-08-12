package rmm.ninjaone.catalog.application.services.queries.ListServices;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.contracts.services.ServiceSrv;

@Component
@RequiredArgsConstructor
public class ListServicesHandler implements Command.Handler<ListServicesQuery, ListServicesResult> {
    private final ServiceSrv serviceSrv;

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
