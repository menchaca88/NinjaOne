package rmm.ninjaone.catalog.application.devices.commands.CreateDevice;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDeviceCommand implements Command<CreateDeviceResult> {
    @NotBlank private String name;
    @NotNull private String subscriptionType;
    @NotNull private Map<String, Object> subscriptionData;
}
