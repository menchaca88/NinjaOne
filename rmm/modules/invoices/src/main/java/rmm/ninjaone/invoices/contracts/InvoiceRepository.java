package rmm.ninjaone.invoices.contracts;

import java.util.List;
import java.util.UUID;

import rmm.ninjaone.buildingblocks.domain.contracts.Repository;
import rmm.ninjaone.invoices.models.Invoice;

@org.springframework.stereotype.Repository
public interface InvoiceRepository extends Repository<Invoice> {
    List<Invoice> findAllByCustomerId(UUID customerId);
    void deleteByCustomerId(UUID customerId);
}
