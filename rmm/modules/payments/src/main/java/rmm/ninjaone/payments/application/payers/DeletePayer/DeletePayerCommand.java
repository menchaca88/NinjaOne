package rmm.ninjaone.payments.application.payers.DeletePayer;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletePayerCommand implements Command<DeletePayerResult> {
    @NotNull private UUID id;
}
