package rmm.ninjaone.catalog.application.devices.commands.CreateDevice;

import javax.validation.constraints.NotBlank;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDeviceCommand implements Command<CreateDeviceResult> {
    @NotBlank private String name;
}
