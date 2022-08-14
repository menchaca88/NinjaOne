package rmm.ninjaone.payments.application.devices.DeleteType;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteTypeCommand implements Command<DeleteTypeResult> {
    @NotNull private UUID typeId;
}
