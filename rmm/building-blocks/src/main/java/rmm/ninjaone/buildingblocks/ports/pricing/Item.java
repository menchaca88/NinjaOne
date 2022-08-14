package rmm.ninjaone.buildingblocks.ports.pricing;

import java.util.UUID;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Item {
    private final UUID typeId;
    private final Integer count;

    protected Item(UUID typeId, Integer count) {
        this.typeId = typeId;
        this.count = count;
    }

    public static Item forService(@NonNull UUID typeId) {
        return new Item(typeId, null);
    }

    public static Item forDevice(@NonNull UUID typeId, int count) {
        return new Item(typeId, count);
    }

    public boolean hasCount() {
        return count != null;
    }
}
