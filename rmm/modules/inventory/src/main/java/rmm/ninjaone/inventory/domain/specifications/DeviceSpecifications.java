package rmm.ninjaone.inventory.domain.specifications;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import rmm.ninjaone.inventory.domain.models.Device;

public interface DeviceSpecifications {
    public static Specification<Device> findById(UUID id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Device> findByClientId(UUID clientId) {
        return (root, query, cb) -> cb.equal(root.get("clientId"), clientId);
    }

    public static Specification<Device> findByTypeId(UUID typeId) {
        return (root, query, cb) -> cb.equal(root.get("typeId"), typeId);
    }

    public static Specification<Device> findByTypeAndClientId(UUID typeId, UUID clientId) {
        return (root, query, cb) -> cb.and(
            cb.equal(root.get("typeId"), typeId),
            cb.equal(root.get("clientId"), clientId));
    }
}
