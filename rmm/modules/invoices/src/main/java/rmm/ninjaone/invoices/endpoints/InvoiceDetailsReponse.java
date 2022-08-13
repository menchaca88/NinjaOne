package rmm.ninjaone.invoices.endpoints;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceDetailsReponse {
    private UUID id;
    private UUID customerId;
    private Date date;
    private double total;
    private List<LineItemResponse> items;
}
