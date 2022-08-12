package rmm.ninjaone.catalog.application.subscriptions.queries.ListSubscriptionTypes;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.contracts.SubscriptionSrv;

@Component
@RequiredArgsConstructor
public class ListSubscriptionTypesHandler implements Command.Handler<ListSubscriptionTypesQuery, ListSubscriptionTypesResult> {
    private final SubscriptionSrv subscriptionSrv;

    @Override
    public ListSubscriptionTypesResult handle(ListSubscriptionTypesQuery command) {
        var deviceTypes = subscriptionSrv.getDeviceTypes();
        var serviceTypes = subscriptionSrv.getServiceTypes();

        var result = new ListSubscriptionTypesResult();
        result.setDevices(deviceTypes);
        result.setServices(serviceTypes);

        return result;
    }
}
