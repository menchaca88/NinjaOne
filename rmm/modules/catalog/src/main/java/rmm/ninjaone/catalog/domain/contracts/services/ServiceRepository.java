package rmm.ninjaone.catalog.domain.contracts.services;

import rmm.ninjaone.buildingblocks.domain.contracts.Repository;
import rmm.ninjaone.catalog.domain.models.services.Service;

@org.springframework.stereotype.Repository
public interface ServiceRepository extends Repository<Service> {
    
}
