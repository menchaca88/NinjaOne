package rmm.ninjaone.inventory.application.listeners.users;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.users.UserDeletedEvent;
import rmm.ninjaone.inventory.application.clients.commands.DeleteClient.DeleteClientCommand;

@Component("ClientDeletedEventHandler")
@RequiredArgsConstructor
public class UserDeletedEventHandler implements Notification.Handler<UserDeletedEvent> {
    private final Pipeline pipeline;

    @Override
    public void handle(UserDeletedEvent event) {
        var command = new DeleteClientCommand();
        command.setId(event.getEntityId());

        pipeline.send(command);
    }
}
