package rmm.ninjaone.payments.application.payers.CreatePayer;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePayerCommand implements Command<CreatePayerResult> {
    @NotNull private UUID id;
    @NotBlank private String name;
}
