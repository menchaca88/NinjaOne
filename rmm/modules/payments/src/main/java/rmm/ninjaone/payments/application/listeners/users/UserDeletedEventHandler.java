package rmm.ninjaone.payments.application.listeners.users;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.users.UserDeletedEvent;
import rmm.ninjaone.payments.application.payers.DeletePayer.DeletePayerCommand;

@Component("PayerDeletedEventHandler")
@RequiredArgsConstructor
public class UserDeletedEventHandler implements Notification.Handler<UserDeletedEvent> {
    private final Pipeline pipeline;

    @Override
    public void handle(UserDeletedEvent event) {
        var command = new DeletePayerCommand();
        command.setId(event.getEntityId());

        pipeline.send(command);
    }
}
