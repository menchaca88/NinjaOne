package rmm.ninjaone.buildingblocks.ports.pricing;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class PricedPackage extends Package {
    private final List<PricedItem> pricedItems;

    public PricedPackage(Package original, List<PricedItem> priceds) {
        super(original.getItems());

        var map = getItems()
            .stream()
            .collect(Collectors.toMap(Item::getTypeId, Function.identity()));
        
        for (var item : priceds)
            if (!map.containsKey(item.getTypeId()))
                throw new IllegalArgumentException();

        this.pricedItems = priceds;
    }

    public boolean fullyPriced() {
        return pricedItems.size() == getItems().size();
    }
}
