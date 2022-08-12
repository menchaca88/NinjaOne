package rmm.ninjaone.catalog.application.devices.commands.CreateDevice;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.contracts.DeviceSrv;

@Component
@RequiredArgsConstructor
public class CreateDeviceHandler implements Command.Handler<CreateDeviceCommand, CreateDeviceResult> {
    private final DeviceSrv deviceSrv;

    @Override
    public CreateDeviceResult handle(CreateDeviceCommand command) { 
        var device = deviceSrv.create(command.getName(), null); //TODO: subscription

        var result = new CreateDeviceResult();
        result.setId(device.getId());
        result.setName(device.getName());
        result.setSku(device.getSku().getRaw());
            
        return result;
    }
}
