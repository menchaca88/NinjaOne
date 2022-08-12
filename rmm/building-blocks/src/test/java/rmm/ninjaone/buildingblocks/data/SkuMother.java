package rmm.ninjaone.buildingblocks.data;

import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;

public class SkuMother extends ObjectMother {
    public static Sku random(Class<?> clazz) {
        var sku = Sku
            .For(clazz)
            .model(StringMother.random())
            .build();

        return sku;
    }
}
