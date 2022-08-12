package rmm.ninjaone.catalog.application.services.commands.UpdateService;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateServiceCommand implements Command<UpdateServiceResult> {
    @NotNull private UUID id;
    @NotNull private String name;
}
