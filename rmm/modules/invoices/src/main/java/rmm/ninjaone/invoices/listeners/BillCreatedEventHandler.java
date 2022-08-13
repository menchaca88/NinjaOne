package rmm.ninjaone.invoices.listeners;

import java.time.YearMonth;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Notification;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.payments.BillCreatedEvent;
import rmm.ninjaone.invoices.contracts.InvoiceRepository;
import rmm.ninjaone.invoices.models.Invoice;

@Component
@RequiredArgsConstructor
public class BillCreatedEventHandler implements Notification.Handler<BillCreatedEvent> {
    private final InvoiceRepository repository;

    @Override
    public void handle(BillCreatedEvent event) {
        var customerInvoices = repository.findAllByCustomerId(event.getClientId());
        var toReplace = customerInvoices
            .stream()
            .filter(i -> {
                var ym1 = YearMonth.from(event.getDate().toInstant());
                var ym2 = YearMonth.from(i.getDate().toInstant());

                return ym1.equals(ym2);
            })
            .toList();

        repository.deleteAll(toReplace);

        var invoice = Invoice.create(event.getEntityId(), event.getClientId(), event.getDate());

        for (var item : event.getDevices())
            invoice.addItem(item.getName(), item.getCount(), item.getCharged());

        for (var item : event.getServices())
            invoice.addItem(item.getName(), item.getCharged());

        repository.save(invoice);
    }
}
