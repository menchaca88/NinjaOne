package rmm.ninjaone.catalog.application.devices.commands.UpdateDevice;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.contracts.DeviceSrv;

@Component
@RequiredArgsConstructor
public class UpdateDeviceHandler implements Command.Handler<UpdateDeviceCommand, UpdateDeviceResult> {
    private final DeviceSrv deviceSrv;

    @Override
    public UpdateDeviceResult handle(UpdateDeviceCommand command) {
        var device = deviceSrv.update(command.getId(), command.getName());

        var result = new UpdateDeviceResult();
        result.setId(device.getId());
        result.setName(device.getName());
        result.setSku(device.getSku().getRaw());
            
        return result;
    }
}
