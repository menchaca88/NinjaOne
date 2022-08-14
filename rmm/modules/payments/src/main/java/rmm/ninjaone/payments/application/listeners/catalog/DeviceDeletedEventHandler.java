package rmm.ninjaone.payments.application.listeners.catalog;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.catalog.DeviceDeletedEvent;
import rmm.ninjaone.payments.application.devices.DeleteType.DeleteTypeCommand;

@Component("DeviceTypeDeletedEventHandler")
@RequiredArgsConstructor
public class DeviceDeletedEventHandler implements Notification.Handler<DeviceDeletedEvent> {
    private final Pipeline pipeline;

    @Override
    public void handle(DeviceDeletedEvent event) {
        var command = new DeleteTypeCommand();
        command.setTypeId(event.getEntityId());

        pipeline.send(command);
    }
}
