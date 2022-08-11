package rmm.ninjaone.identity.data;

import java.util.UUID;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.identity.application.commands.UpdateUser.UpdateUserCommand;

public class UpdateUserMother extends ObjectMother {
    public static UpdateUserCommand random() {
        var command = new UpdateUserCommand();

        command.setId(UUID.randomUUID());
        command.setName(StringMother.random());
        command.setPassword(StringMother.random());

        return command;
    }
}
