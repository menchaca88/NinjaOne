package rmm.ninjaone.payments.application.listeners.inventory;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.inventory.DeviceDeletedEvent;
import rmm.ninjaone.payments.application.devices.DeleteDevice.DeleteDeviceCommand;

@Component("DeviceChargeDeletedEventHandler")
@RequiredArgsConstructor
public class DeviceDeletedEventHandler implements Notification.Handler<DeviceDeletedEvent> {
    private final Pipeline pipeline;

    @Override
    public void handle(DeviceDeletedEvent event) {
        var command = new DeleteDeviceCommand();
        command.setId(event.getEntityId());

        pipeline.send(command);
    }
}
