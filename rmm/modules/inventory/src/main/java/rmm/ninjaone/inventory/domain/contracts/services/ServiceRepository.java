package rmm.ninjaone.inventory.domain.contracts.services;

import rmm.ninjaone.buildingblocks.domain.contracts.Repository;
import rmm.ninjaone.inventory.domain.models.Service;

@org.springframework.stereotype.Repository("ServiceRepository")
public interface ServiceRepository extends Repository<Service> {
    
}
