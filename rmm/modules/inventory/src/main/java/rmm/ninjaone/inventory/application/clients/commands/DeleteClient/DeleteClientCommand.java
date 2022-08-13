package rmm.ninjaone.inventory.application.clients.commands.DeleteClient;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteClientCommand implements Command<DeleteClientResult> {
    @NotNull private UUID id;
}
