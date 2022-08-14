package rmm.ninjaone.payments.application.listeners.catalog;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.catalog.ServiceRenamedEvent;
import rmm.ninjaone.payments.application.services.RenameType.RenameTypeCommand;

@Component("ServiceTypeRenamedEventHandler")
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
