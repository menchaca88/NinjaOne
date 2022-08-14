package rmm.ninjaone.payments.application.services.CreateService;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateServiceCommand implements Command<CreateServiceResult> {
    @NotNull private UUID id;
    @NotNull private UUID typeId;
    @NotNull private String typeName;
    @NotNull private UUID payerId;
}
