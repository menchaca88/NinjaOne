package rmm.ninjaone.identity.domain.specifications;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import rmm.ninjaone.buildingblocks.domain.valueObjects.Email;
import rmm.ninjaone.identity.domain.models.RmmUser;

public interface UserSpecifications {
    public static Specification<RmmUser> findById(UUID id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }
    
    public static Specification<RmmUser> findByEmail(Email email) {
        return (root, query, cb) -> cb.equal(root.get("email"), email);
    }
}
