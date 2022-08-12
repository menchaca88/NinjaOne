package rmm.ninjaone.catalog.application.subscriptions.queries.ListSubscriptionTypes;

import org.springframework.stereotype.Component;

import rmm.ninjaone.buildingblocks.application.bases.BaseHandler;
import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.catalog.domain.contracts.subscriptions.SubscriptionSrv;

@Component
public class ListSubscriptionTypesHandler extends BaseHandler<ListSubscriptionTypesQuery, ListSubscriptionTypesResult> {
    private final SubscriptionSrv subscriptionSrv;

    public ListSubscriptionTypesHandler(UserContext context, SubscriptionSrv subscriptionSrv) {
        super(context);
        this.subscriptionSrv = subscriptionSrv;
    }

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
