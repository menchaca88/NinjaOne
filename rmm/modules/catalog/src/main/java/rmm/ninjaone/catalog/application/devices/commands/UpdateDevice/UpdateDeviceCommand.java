package rmm.ninjaone.catalog.application.devices.commands.UpdateDevice;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDeviceCommand implements Command<UpdateDeviceResult> {
    @NotNull private UUID id;
    @NotNull private String name;
}
