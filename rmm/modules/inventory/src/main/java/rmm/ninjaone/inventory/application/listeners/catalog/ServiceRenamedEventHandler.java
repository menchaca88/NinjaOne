package rmm.ninjaone.inventory.application.listeners.catalog;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.catalog.ServiceRenamedEvent;
import rmm.ninjaone.inventory.application.services.commands.RenameType.RenameTypeCommand;

@Component
@RequiredArgsConstructor
public class ServiceRenamedEventHandler implements Notification.Handler<ServiceRenamedEvent> {
    private final Pipeline pipeline;

    @Override
    public void handle(ServiceRenamedEvent event) {
        var command = new RenameTypeCommand();
        command.setTypeId(event.getEntityId());
        command.setName(event.getNewName());

        pipeline.send(command);
    }
}
