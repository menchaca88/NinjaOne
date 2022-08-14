package rmm.ninjaone.payments.application.listeners.inventory;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.inventory.DeviceCreatedEvent;
import rmm.ninjaone.payments.application.devices.CreateDevice.CreateDeviceCommand;

@Component("DeviceChargeCreatedEventHandler")
@RequiredArgsConstructor
public class DeviceCreatedEventHandler implements Notification.Handler<DeviceCreatedEvent> {
    private final Pipeline pipeline;

    @Override
    public void handle(DeviceCreatedEvent event) {
        var command = new CreateDeviceCommand();
        command.setId(event.getEntityId());
        command.setPayerId(event.getClientId());
        command.setTypeId(event.getTypeId());
        command.setTypeName(event.getTypeName());
        command.setCount(event.getCount());
        
        pipeline.send(command);
    }
}
