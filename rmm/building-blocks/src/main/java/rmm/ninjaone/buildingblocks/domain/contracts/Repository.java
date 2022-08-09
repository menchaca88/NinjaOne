package rmm.ninjaone.buildingblocks.domain.contracts;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import rmm.ninjaone.buildingblocks.domain.bases.AggregateRoot;

public interface Repository<T extends AggregateRoot> extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T>, CustomizedActions<T> {
    
}