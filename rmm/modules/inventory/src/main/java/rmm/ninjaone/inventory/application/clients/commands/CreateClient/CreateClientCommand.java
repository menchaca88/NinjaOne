package rmm.ninjaone.inventory.application.clients.commands.CreateClient;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClientCommand implements Command<CreateClientResult> {
    @NotNull private UUID id;
    @NotBlank private String name;
}
