package rmm.ninjaone.buildingblocks.ports.pricing;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class PricedItem extends Item {
    private final double price;

    public PricedItem(@NonNull Item original, double price) {
        super(original.getTypeId(), original.getCount());
        this.price = price;
    }
}
