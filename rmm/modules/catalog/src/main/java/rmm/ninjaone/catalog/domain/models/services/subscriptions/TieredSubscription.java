package rmm.ninjaone.catalog.domain.models.services.subscriptions;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TieredSubscription extends ServiceSubscription {
    private final List<Tier> tiers;

    @Getter
    @RequiredArgsConstructor
    public static class Tier {
        private final int count;
        private final double cost;
    }
}