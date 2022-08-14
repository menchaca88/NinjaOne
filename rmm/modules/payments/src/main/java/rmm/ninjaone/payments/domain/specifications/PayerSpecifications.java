package rmm.ninjaone.payments.domain.specifications;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import rmm.ninjaone.payments.domain.models.Payer;

public interface PayerSpecifications {
    public static Specification<Payer> findById(UUID id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }
}
