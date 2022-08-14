package rmm.ninjaone.pricing.data;

import java.util.UUID;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.ports.pricing.Item;

public class ItemMother extends ObjectMother {
    public static Item device() {
        return Item.forDevice(UUID.randomUUID(), random.nextInt(5, 11));
    }

    public static Item service() {
        return Item.forService(UUID.randomUUID());
    }
}
