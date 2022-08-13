package rmm.ninjaone.invoices.listeners;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.users.UserDeletedEvent;
import rmm.ninjaone.invoices.contracts.InvoiceRepository;

@Component("CustomerDeletedEventHandler")
@RequiredArgsConstructor
public class UserDeletedEventHandler implements Notification.Handler<UserDeletedEvent> {
    private final InvoiceRepository repository;

    @Override
    public void handle(UserDeletedEvent event) {
        repository.deleteByCustomerId(event.getEntityId());
    }
}
