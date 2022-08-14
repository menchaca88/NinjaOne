package rmm.ninjaone.payments.domain.specifications;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import rmm.ninjaone.payments.domain.models.DeviceCharge;

public interface DeviceSpecifications {
    public static Specification<DeviceCharge> findById(UUID id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<DeviceCharge> findByTypeId(UUID typeId) {
        return (root, query, cb) -> cb.equal(root.get("typeId"), typeId);
    }
}
