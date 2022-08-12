package rmm.ninjaone.catalog.domain.models.devices.subscriptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MonthlyFixedDeviceSubscription extends DeviceSubscription {
    private final double monthlyCost;
}
