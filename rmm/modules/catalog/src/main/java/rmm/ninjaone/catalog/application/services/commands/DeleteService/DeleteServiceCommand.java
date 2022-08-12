package rmm.ninjaone.catalog.application.services.commands.DeleteService;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteServiceCommand implements Command<DeleteServiceResult> {
    @NotNull private UUID id;
}
