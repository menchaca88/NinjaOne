package rmm.ninjaone.inventory.application.clients.commands.UpdateClient;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateClientCommand implements Command<UpdateClientResult> {
    @NotNull private UUID id;
    @NotBlank private String name;
}
