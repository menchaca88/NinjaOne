package rmm.ninjaone.inventory.application.listeners.catalog;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.catalog.ServiceDeletedEvent;
import rmm.ninjaone.inventory.application.services.commands.DeleteType.DeleteTypeCommand;

@Component
@RequiredArgsConstructor
public class ServiceDeletedEventHandler implements Notification.Handler<ServiceDeletedEvent> {
    private final Pipeline pipeline;

    @Override
    public void handle(ServiceDeletedEvent event) {
        var command = new DeleteTypeCommand();
        command.setTypeId(event.getEntityId());

        pipeline.send(command);
    }
}
