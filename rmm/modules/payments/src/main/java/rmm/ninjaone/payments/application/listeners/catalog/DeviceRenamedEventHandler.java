package rmm.ninjaone.payments.application.listeners.catalog;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.catalog.DeviceRenamedEvent;
import rmm.ninjaone.payments.application.devices.RenameType.RenameTypeCommand;

@Component("DeviceTypeRenamedEventHandler")
@RequiredArgsConstructor
public class DeviceRenamedEventHandler implements Notification.Handler<DeviceRenamedEvent> {
    private final Pipeline pipeline;

    @Override
    public void handle(DeviceRenamedEvent event) {
        var command = new RenameTypeCommand();
        command.setTypeId(event.getEntityId());
        command.setName(event.getNewName());

        pipeline.send(command);
    }
}
