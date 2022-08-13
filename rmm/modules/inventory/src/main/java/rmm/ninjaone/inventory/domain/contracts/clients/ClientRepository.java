package rmm.ninjaone.inventory.domain.contracts.clients;

import rmm.ninjaone.buildingblocks.domain.contracts.Repository;
import rmm.ninjaone.inventory.domain.models.Client;

@org.springframework.stereotype.Repository
public interface ClientRepository extends Repository<Client> {
    
}
