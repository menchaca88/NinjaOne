package rmm.ninjaone.catalog.domain.models.devices.subscriptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PerUnitSubscription extends DeviceSubscription {
    private final double unitCost;
}
