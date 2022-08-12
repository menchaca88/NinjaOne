package rmm.ninjaone.catalog.domain.models.services.subscriptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PerDeviceSubscription extends ServiceSubscription {
    private final double unitCost;
}
