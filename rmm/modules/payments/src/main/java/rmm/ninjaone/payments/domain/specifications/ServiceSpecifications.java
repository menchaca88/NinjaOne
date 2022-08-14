package rmm.ninjaone.payments.domain.specifications;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import rmm.ninjaone.payments.domain.models.ServiceCharge;

public interface ServiceSpecifications {
    public static Specification<ServiceCharge> findById(UUID id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<ServiceCharge> findByTypeId(UUID typeId) {
        return (root, query, cb) -> cb.equal(root.get("typeId"), typeId);
    }
}
