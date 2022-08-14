package rmm.ninjaone.payments.application.listeners.inventory;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.inventory.ServiceCreatedEvent;
import rmm.ninjaone.payments.application.services.CreateService.CreateServiceCommand;

@Component("ServiceChargeCreatedEventHandler")
@RequiredArgsConstructor
public class ServiceCreatedEventHandler implements Notification.Handler<ServiceCreatedEvent> {
    private final Pipeline pipeline;

    @Override
    public void handle(ServiceCreatedEvent event) {
        var command = new CreateServiceCommand();
        command.setId(event.getEntityId());
        command.setPayerId(event.getClientId());
        command.setTypeId(event.getTypeId());
        command.setTypeName(event.getTypeName());
        
        pipeline.send(command);
    }
}
