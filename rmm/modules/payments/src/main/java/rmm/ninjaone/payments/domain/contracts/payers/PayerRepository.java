package rmm.ninjaone.payments.domain.contracts.payers;

import rmm.ninjaone.buildingblocks.domain.contracts.Repository;
import rmm.ninjaone.payments.domain.models.Payer;

@org.springframework.stereotype.Repository
public interface PayerRepository extends Repository<Payer> {
    
}