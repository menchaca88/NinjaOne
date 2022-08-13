package rmm.ninjaone.inventory.domain.specifications;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import rmm.ninjaone.inventory.domain.models.Service;

public interface ServiceSpecifications {
    public static Specification<Service> findById(UUID id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Service> findByClientId(UUID clientId) {
        return (root, query, cb) -> cb.equal(root.get("clientId"), clientId);
    }

    public static Specification<Service> findByTypeId(UUID typeId) {
        return (root, query, cb) -> cb.equal(root.get("typeId"), typeId);
    }

    public static Specification<Service> findByTypeAndClientId(UUID typeId, UUID clientId) {
        return (root, query, cb) -> cb.and(
            cb.equal(root.get("typeId"), typeId),
            cb.equal(root.get("clientId"), clientId));
    }
}
