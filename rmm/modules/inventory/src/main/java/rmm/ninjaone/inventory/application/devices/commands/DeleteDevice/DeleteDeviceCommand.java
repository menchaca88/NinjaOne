package rmm.ninjaone.inventory.application.devices.commands.DeleteDevice;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteDeviceCommand implements Command<DeleteDeviceResult> {
    @NotNull private UUID id;
}
