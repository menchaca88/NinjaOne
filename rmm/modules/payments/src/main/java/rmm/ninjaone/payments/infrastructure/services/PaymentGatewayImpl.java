package rmm.ninjaone.payments.infrastructure.services;

import org.springframework.stereotype.Service;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.events.payments.BillCreatedEvent;
import rmm.ninjaone.payments.domain.contracts.payments.PaymentGateway;
import rmm.ninjaone.payments.domain.models.Bill;

@Service
@RequiredArgsConstructor
public class PaymentGatewayImpl implements PaymentGateway {
    private final Pipeline pipeline;
    
    @Override
    public void charge(Bill bill) {
        var event = bill
            .getDomainEvents()
            .stream()
            .filter(e -> e instanceof BillCreatedEvent)
            .findFirst();

        if (event.isPresent())
            pipeline.send((BillCreatedEvent)event.get());
    }
}
