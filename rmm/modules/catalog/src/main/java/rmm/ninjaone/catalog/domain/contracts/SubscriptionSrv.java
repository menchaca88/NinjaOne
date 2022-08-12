package rmm.ninjaone.catalog.domain.contracts;

import java.util.List;

public interface SubscriptionSrv {
    List<String> getDeviceTypes();
    List<String> getServiceTypes();
}
