package rmm.ninjaone.identity.data;

import java.util.UUID;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.identity.application.commands.DeleteUser.DeleteUserCommand;

public class DeleteUserMother extends ObjectMother {
    public static DeleteUserCommand random() {
        var command = new DeleteUserCommand();

        command.setId(UUID.randomUUID());

        return command;
    }
}
