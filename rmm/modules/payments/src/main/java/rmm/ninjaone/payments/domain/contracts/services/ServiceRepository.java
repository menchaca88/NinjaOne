package rmm.ninjaone.payments.domain.contracts.services;

import rmm.ninjaone.buildingblocks.domain.contracts.Repository;
import rmm.ninjaone.payments.domain.models.ServiceCharge;

@org.springframework.stereotype.Repository("ServiceChargeRepository")
public interface ServiceRepository extends Repository<ServiceCharge> {
    
}