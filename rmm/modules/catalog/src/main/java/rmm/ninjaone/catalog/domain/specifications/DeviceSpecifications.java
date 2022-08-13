package rmm.ninjaone.catalog.domain.specifications;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;
import rmm.ninjaone.catalog.domain.models.devices.DeviceType;

public interface DeviceSpecifications {
    public static Specification<DeviceType> findById(UUID id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<DeviceType> findByName(String name) {
        return (root, query, cb) -> cb.equal(root.get("name"), name);
    }

    public static Specification<DeviceType> findByNameExcept(String name, UUID exceptId) {
        return (root, query, cb) -> cb.and(
            cb.equal(root.get("name"), name),
            cb.notEqual(root.get("id"), exceptId));
    }
    
    public static Specification<DeviceType> findBySku(Sku sku) {
        return (root, query, cb) -> cb.equal(root.get("sku"), sku);
    }
}
