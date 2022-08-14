package rmm.ninjaone.payments.domain.models;

import java.util.UUID;

import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.domain.bases.AggregateRoot;

@Getter
@Entity
public abstract class Charge extends AggregateRoot {
    @Type(type="uuid-char") private UUID typeId;
    @Type(type="uuid-char") private UUID payerId;
    @Setter @NonNull private String typeName;

    protected Charge(UUID id, UUID payerId, UUID typeId, String typeName) {
        super(id);
        this.payerId = payerId;
        this.typeId = typeId;
        this.typeName = typeName;
    }

    protected Charge() {
        super();
    }
}