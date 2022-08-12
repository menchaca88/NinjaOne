package rmm.ninjaone.catalog.domain.specifications;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;
import rmm.ninjaone.catalog.domain.models.devices.Device;

public interface DeviceSpecifications {
    public static Specification<Device> findById(UUID id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Device> findByName(String name) {
        return (root, query, cb) -> cb.equal(root.get("name"), name);
    }

    public static Specification<Device> findByNameExcept(String name, UUID exceptId) {
        return (root, query, cb) -> cb.and(
            cb.equal(root.get("name"), name),
            cb.notEqual(root.get("id"), exceptId));
    }
    
    public static Specification<Device> findBySku(Sku sku) {
        return (root, query, cb) -> cb.equal(root.get("sku"), sku);
    }
}
