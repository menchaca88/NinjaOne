package rmm.ninjaone.buildingblocks.ports.pricing;

import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Package {
    private final List<Item> items;

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }
}
