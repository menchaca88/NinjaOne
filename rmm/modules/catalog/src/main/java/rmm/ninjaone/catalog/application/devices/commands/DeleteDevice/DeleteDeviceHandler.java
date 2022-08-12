package rmm.ninjaone.catalog.application.devices.commands.DeleteDevice;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.catalog.domain.contracts.devices.DeviceSrv;

@Component
@RequiredArgsConstructor
public class DeleteDeviceHandler implements Command.Handler<DeleteDeviceCommand, DeleteDeviceResult> {
    private final DeviceSrv deviceSrv;

    @Override
    public DeleteDeviceResult handle(DeleteDeviceCommand command) {
        var device = deviceSrv.delete(command.getId());

        var result = new DeleteDeviceResult();
        result.setId(device.getId());
        result.setName(device.getName());
        result.setSku(device.getSku().getRaw());
        result.setSubscription(device.getSubscription().getName());
            
        return result;
    }
}
