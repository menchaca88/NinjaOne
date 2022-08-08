package rmm.ninjaone.api.data;

import rmm.ninjaone.api.services.TemporalUser;
import rmm.ninjaone.buildingblocks.data.EmailMother;
import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;

public class UserMother extends ObjectMother {
    public static TemporalUser random() {
        var user = new TemporalUser();

        user.id = random.nextInt();
        user.name = StringMother.random();
        user.email = EmailMother.random();
        user.password = StringMother.random(8, 16);

        return user;
    }
}
