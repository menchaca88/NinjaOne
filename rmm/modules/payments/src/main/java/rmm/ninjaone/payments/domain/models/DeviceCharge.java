package rmm.ninjaone.payments.domain.models;

import java.util.UUID;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeviceCharge extends Charge {
    private int count;

    private DeviceCharge(UUID id, UUID payerId, UUID typeId, String typeName, int count) {
        super(id, payerId, typeId, typeName);
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count <= 0)
            throw new IllegalArgumentException("count");
        
        this.count = count;
    }

    public static DeviceCharge create(@NonNull UUID id, @NonNull UUID payerId, @NonNull UUID typeId, @NonNull String typeName, int count) {
        return new DeviceCharge(id, payerId, typeId, typeName, count);
    } 
}
