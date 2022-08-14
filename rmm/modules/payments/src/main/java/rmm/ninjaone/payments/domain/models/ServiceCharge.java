package rmm.ninjaone.payments.domain.models;

import java.util.UUID;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServiceCharge extends Charge {
    private ServiceCharge(UUID id, UUID payerId, UUID typeId, String typeName) {
        super(id, payerId, typeId, typeName);
    }

    public static ServiceCharge create(@NonNull UUID id, @NonNull UUID payerId, @NonNull UUID typeId, @NonNull String typeName) {
        return new ServiceCharge(id, payerId, typeId, typeName);
    } 
}
