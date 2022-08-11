package rmm.ninjaone.identity.domain.contracts;

import rmm.ninjaone.buildingblocks.domain.contracts.Repository;
import rmm.ninjaone.identity.domain.models.RmmUser;

@org.springframework.stereotype.Repository
public interface RmmUserRepository extends Repository<RmmUser> {
    
}
