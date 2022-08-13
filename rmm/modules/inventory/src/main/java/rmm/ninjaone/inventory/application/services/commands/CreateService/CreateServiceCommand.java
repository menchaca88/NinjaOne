package rmm.ninjaone.inventory.application.services.commands.CreateService;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateServiceCommand implements Command<CreateServiceResult> {
    @NotNull private UUID typeId;
}
