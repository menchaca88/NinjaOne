package rmm.ninjaone.inventory.domain.specifications;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import rmm.ninjaone.inventory.domain.models.Client;

public interface ClientSpecifications {
    public static Specification<Client> findById(UUID id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }
}
