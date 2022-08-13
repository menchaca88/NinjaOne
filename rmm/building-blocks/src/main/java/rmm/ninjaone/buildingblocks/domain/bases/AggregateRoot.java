package rmm.ninjaone.buildingblocks.domain.bases;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreRemove;

import org.hibernate.annotations.Type;
import org.springframework.data.domain.AbstractAggregateRoot;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class AggregateRoot extends AbstractAggregateRoot<AggregateRoot> {
    @Getter @Setter @Id @Type(type="uuid-char") private UUID id;

    protected AggregateRoot(UUID id) {
        this.id = id;
    }

    protected AggregateRoot() {
        id = UUID.randomUUID();
    }

    @PreRemove
    protected void deleted() {
    }
}
