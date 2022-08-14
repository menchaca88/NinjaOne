package rmm.ninjaone.payments.application.listeners.inventory;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.inventory.ServiceDeletedEvent;
import rmm.ninjaone.payments.application.services.DeleteService.DeleteServiceCommand;

@Component("ServiceChargeDeletedEventHandler")
@RequiredArgsConstructor
public class ServiceDeletedEventHandler implements Notification.Handler<ServiceDeletedEvent> {
    private final Pipeline pipeline;

    @Override
    public void handle(ServiceDeletedEvent event) {
        var command = new DeleteServiceCommand();
        command.setId(event.getEntityId());

        pipeline.send(command);
    }
}
