package rmm.ninjaone.invoices.endpoints;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.invoices.contracts.InvoiceRepository;
import rmm.ninjaone.invoices.exceptions.InvoiceNotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "${api.invoices}")
public class InvoicesController {
    private final InvoiceRepository repository;
    private final ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<ListInvoicesResponse> list() {
        var items = repository.findAllByCustomerId(getCurrentId());

        List<InvoiceResponse> invoices = new ArrayList<InvoiceResponse>();
        modelMapper.map(items, invoices);

        var response = new ListInvoicesResponse();
        response.setInvoices(invoices);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{invoiceId}")
    public ResponseEntity<InvoiceDetailsReponse> details(@PathVariable UUID invoiceId) {
        var invoice = repository
            .findById(invoiceId)
            .orElseThrow(() -> new InvoiceNotFoundException(invoiceId));

        var response = modelMapper.map(invoice, InvoiceDetailsReponse.class);

        return ResponseEntity.ok(response);
    }

    private UUID getCurrentId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        var details = authentication.getDetails();
        if (details instanceof UUID)
            return (UUID)details;

        return null;
    }
}