package rmm.ninjaone.payments.data;

import java.util.UUID;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.payments.domain.models.Payer;

public class PayerMother extends ObjectMother {
    public static Payer random() {
        var payer = Payer.create(UUID.randomUUID(), StringMother.random());

        return payer;
    }

    public static Payer withCharges() {
        var payer = random();

        for (int i = 0; i < 5; i++) {
            payer.addCharge(DeviceMother.random());
            payer.addCharge(ServiceMother.random());
        }

        return payer;
    }
}