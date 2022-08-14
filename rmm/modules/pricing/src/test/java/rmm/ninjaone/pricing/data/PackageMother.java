package rmm.ninjaone.pricing.data;

import java.util.ArrayList;
import java.util.Arrays;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.ports.pricing.Item;
import rmm.ninjaone.buildingblocks.ports.pricing.Package;

public class PackageMother extends ObjectMother {
    public static Package random(Item current, int devices, int services) {
        if (current.hasCount())  devices--;
        else services--;

        var items = new ArrayList<Item>();
        items.add(current);

        for (int i = 0; i < devices; i++)
            items.add(ItemMother.device());

        for (int i = 0; i < services; i++)
            items.add(ItemMother.service());

        return new Package(items);
    }

    public static Package random(Item current, Item... items) {
        var list = new ArrayList<Item>();
        
        list.addAll(Arrays.asList(items));
        list.add(current);

        return new Package(list);
    }
}
