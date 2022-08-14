package rmm.ninjaone.payments.application.devices.CreateDevice;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDeviceCommand implements Command<CreateDeviceResult> {
    @NotNull private UUID id;
    @NotNull private UUID typeId;
    @NotNull private String typeName;
    @NotNull private UUID payerId;
    private int count;
}
