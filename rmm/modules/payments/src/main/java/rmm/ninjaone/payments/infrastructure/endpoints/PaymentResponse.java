package rmm.ninjaone.payments.infrastructure.endpoints;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {
    private UUID invoiceId;
    private double total;
}
