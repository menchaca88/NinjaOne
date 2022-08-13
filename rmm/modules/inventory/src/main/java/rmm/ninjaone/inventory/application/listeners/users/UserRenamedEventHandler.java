package rmm.ninjaone.inventory.application.listeners.users;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.users.UserRenamedEvent;
import rmm.ninjaone.inventory.application.clients.commands.UpdateClient.UpdateClientCommand;

@Component
@RequiredArgsConstructor
public class UserRenamedEventHandler implements Notification.Handler<UserRenamedEvent> {
    private final Pipeline pipeline;

    @Override
    public void handle(UserRenamedEvent event) {
        var command = new UpdateClientCommand();
        command.setId(event.getEntityId());
        command.setName(event.getNewName());

        pipeline.send(command);
    }
}
