package rmm.ninjaone.buildingblocks.ports.catalog;

import java.util.Map;

import lombok.Value;

@Value
public final class SubscriptionData {
    private String type;
    private Map<String, Object> data;
}
