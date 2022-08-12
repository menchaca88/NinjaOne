package rmm.ninjaone.catalog.application.services.commands.CreateService;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.contracts.services.ServiceSrv;
import rmm.ninjaone.catalog.domain.contracts.subscriptions.SubscriptionSrv;
import rmm.ninjaone.catalog.domain.models.Subscription;

@Component
@RequiredArgsConstructor
public class CreateServiceHandler implements Command.Handler<CreateServiceCommand, CreateServiceResult> {
    private final ServiceSrv serviceSrv;
    private final SubscriptionSrv subscriptionSrv;

    @Override
    public CreateServiceResult handle(CreateServiceCommand command) {
        var rawData = new Subscription.RawData();
        rawData.setType(command.getSubscriptionType());
        rawData.setData(command.getSubscriptionData());

        var subscription = subscriptionSrv.serviceCreate(rawData);
        var service = serviceSrv.create(command.getName(), subscription);

        var result = new CreateServiceResult();
        result.setId(service.getId());
        result.setName(service.getName());
        result.setSku(service.getSku().getRaw());
        result.setSubscription(service.getSubscription().getName());
            
        return result;
    }
    
}
