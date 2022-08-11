package rmm.ninjaone.identity.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import rmm.ninjaone.buildingblocks.data.EmailMother;
import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Email;
import rmm.ninjaone.identity.domain.models.RmmRole;
import rmm.ninjaone.identity.domain.models.RmmUser;

public class RmmUserMother extends ObjectMother {
    public static RmmUser random() {
        var user = RmmUser.create(StringMother.random(), RmmRole.User, Email.of(EmailMother.random()));
        user.changePassword(StringMother.random());

        return user;
    }

    public static List<RmmUser> count(Integer count) {
        List<RmmUser> list = new ArrayList<>();
        for (int i = 0; i < count; i++)
            list.add(random());

        return list;
    }

    public static RmmUser withEmail(String email) {
        var user = RmmUser.create(StringMother.random(), RmmRole.User, Email.of(email));
        user.changePassword(StringMother.random());

        return user;
    }

    public static RmmUser withId(UUID id) {
        var user = RmmUser.create(StringMother.random(), RmmRole.User, Email.of(EmailMother.random()));
        user.changePassword(StringMother.random());
        user.setId(id);

        return user;
    }
}
