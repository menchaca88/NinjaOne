package rmm.ninjaone.identity.application.commands.DeleteUser;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteUserCommand implements Command<DeleteUserResult> {
    @NotNull private UUID id;
}
