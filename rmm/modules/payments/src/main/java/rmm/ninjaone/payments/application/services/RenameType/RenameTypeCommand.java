package rmm.ninjaone.payments.application.services.RenameType;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RenameTypeCommand implements Command<RenameTypeResult> {
    @NotNull private UUID typeId;
    @NotBlank private String name;
}
