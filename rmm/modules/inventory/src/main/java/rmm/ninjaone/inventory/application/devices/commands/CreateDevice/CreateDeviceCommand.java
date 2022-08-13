package rmm.ninjaone.inventory.application.devices.commands.CreateDevice;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDeviceCommand implements Command<CreateDeviceResult> {
    @NotNull private UUID typeId;
    private int count;
}
