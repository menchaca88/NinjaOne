package rmm.ninjaone.payments.application.listeners.users;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.users.UserRenamedEvent;
import rmm.ninjaone.payments.application.payers.UpdatePayer.UpdatePayerCommand;

@Component("PayerRenamedEventHandler")
@RequiredArgsConstructor
public class UserRenamedEventHandler implements Notification.Handler<UserRenamedEvent> {
    private final Pipeline pipeline;

    @Override
    public void handle(UserRenamedEvent event) {
        var command = new UpdatePayerCommand();
        command.setId(event.getEntityId());
        command.setName(event.getNewName());

        pipeline.send(command);
    }
}
