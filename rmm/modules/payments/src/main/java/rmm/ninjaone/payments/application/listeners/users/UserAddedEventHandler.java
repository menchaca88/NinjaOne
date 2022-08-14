package rmm.ninjaone.payments.application.listeners.users;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.users.UserAddedEvent;
import rmm.ninjaone.payments.application.payers.CreatePayer.CreatePayerCommand;

@Component("PayerAddedEventHandler")
@RequiredArgsConstructor
public class UserAddedEventHandler implements Notification.Handler<UserAddedEvent> {
    private final Pipeline pipeline;

    @Override
    public void handle(UserAddedEvent event) {
        var command = new CreatePayerCommand();
        command.setId(event.getEntityId());
        command.setName(event.getName());

        pipeline.send(command);
    }
}
