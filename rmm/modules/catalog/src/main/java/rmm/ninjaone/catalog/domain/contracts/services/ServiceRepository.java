package rmm.ninjaone.catalog.domain.contracts.services;

import rmm.ninjaone.buildingblocks.domain.contracts.Repository;
import rmm.ninjaone.catalog.domain.models.services.ServiceType;

@org.springframework.stereotype.Repository("ServiceTypeRepository")
public interface ServiceRepository extends Repository<ServiceType> {
    
}
