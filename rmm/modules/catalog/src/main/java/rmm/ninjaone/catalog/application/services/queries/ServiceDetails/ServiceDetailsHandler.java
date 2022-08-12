package rmm.ninjaone.catalog.application.services.queries.ServiceDetails;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;
import rmm.ninjaone.catalog.domain.contracts.services.ServiceSrv;
import rmm.ninjaone.catalog.domain.models.services.Service;

@Component
@RequiredArgsConstructor
public class ServiceDetailsHandler implements Command.Handler<ServiceDetailsQuery, ServiceDetailsResult> {
    private final ServiceSrv serviceSrv;

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
