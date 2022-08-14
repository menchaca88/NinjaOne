package rmm.ninjaone.payments.application.listeners.inventory;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.inventory.DeviceCountUpdatedEvent;
import rmm.ninjaone.payments.application.devices.updateDevice.UpdateDeviceCommand;

@Component
@RequiredArgsConstructor
public class DeviceCountUpdatedEventHandler implements Notification.Handler<DeviceCountUpdatedEvent> {
    private final Pipeline pipeline;

    @Override
    public void handle(DeviceCountUpdatedEvent event) {
        var command = new UpdateDeviceCommand();
        command.setId(event.getEntityId());
        command.setCount(event.getNewCount());

        pipeline.send(command);
    }
}
