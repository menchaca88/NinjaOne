package rmm.ninjaone.catalog.application.services.commands.CreateService;

import javax.validation.constraints.NotBlank;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateServiceCommand implements Command<CreateServiceResult> {
    @NotBlank private String name;
}
