package rmm.ninjaone.invoices.endpoints;

import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceResponse {
    private UUID id;
    private UUID customerId;
    private String customerName;
    private Date date;
    private double total;
}
