package rmm.ninjaone.catalog.domain.models.services.subscriptions;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PerDeviceTypeServiceSubscription extends ServiceSubscription {
    private final List<Type> types;

    @Override
    public boolean relatedTo(UUID deviceId) {
        return types.stream().anyMatch(t -> deviceId.equals(t.getDeviceId()));
    }

    @Override
    public List<UUID> getRelatedDevices() {
        return types.stream().map(t -> t.getDeviceId()).toList();
    }

    @Getter
    @RequiredArgsConstructor
    public static class Type {
        private final UUID deviceId;
        private final double cost;
    }
}
