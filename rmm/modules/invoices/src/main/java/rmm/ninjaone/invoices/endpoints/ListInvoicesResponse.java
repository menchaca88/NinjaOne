package rmm.ninjaone.invoices.endpoints;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListInvoicesResponse {
    private List<InvoiceResponse> invoices;
}