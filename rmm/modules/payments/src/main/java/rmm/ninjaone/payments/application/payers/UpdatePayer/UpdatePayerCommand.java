package rmm.ninjaone.payments.application.payers.UpdatePayer;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePayerCommand implements Command<UpdatePayerResult> {
    @NotNull private UUID id;
    @NotBlank private String name;
}
