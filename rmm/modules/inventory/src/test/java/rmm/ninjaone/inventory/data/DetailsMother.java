package rmm.ninjaone.inventory.data;

import java.util.HashMap;
import java.util.UUID;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.buildingblocks.ports.catalog.Details;
import rmm.ninjaone.buildingblocks.ports.catalog.SubscriptionData;

public class DetailsMother extends ObjectMother {
    public static Details randomType() {
        var data = new HashMap<String, Object>();
        data.put("monthlyCost", 10);
        var sub = new SubscriptionData("MonthlyFixed", data);

        var type = new Details(UUID.randomUUID(), StringMother.random(), StringMother.random(), sub);

        return type;
    }
}
