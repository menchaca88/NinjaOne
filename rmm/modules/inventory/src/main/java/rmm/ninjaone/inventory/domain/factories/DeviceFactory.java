package rmm.ninjaone.inventory.domain.factories;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.ports.catalog.CatalogPort;
import rmm.ninjaone.inventory.domain.exceptions.DeviceTypeNotFoundException;
import rmm.ninjaone.inventory.domain.models.Device;

@Service
@RequiredArgsConstructor
public class DeviceFactory {
    private final CatalogPort catalog;

    public DeviceBuilder ofType(UUID typeId) {
        var type = catalog.findDevice(typeId);
        if (type == null)
            throw new DeviceTypeNotFoundException(typeId);

        var builder = new DeviceBuilder();
        return builder
            .ofType(type.getId())
            .typeName(type.getName());
    }
    
    public static class DeviceBuilder {
        private UUID typeId;
        private String typeName;
        private int count;
        private UUID clientId;

        private DeviceBuilder ofType(@NonNull UUID typeId) {
            this.typeId = typeId;
            return this;
        }

        private DeviceBuilder typeName(@NonNull String typeName) {
            this.typeName = typeName;
            return this;
        }

        public DeviceBuilder withCount(int count) {
            this.count = count;
            return this;
        }

        public DeviceBuilder ownedBy(@NonNull UUID clientId) {
            this.clientId = clientId;
            return this;
        }

        public Device build() {
            return Device.create(count, clientId, typeId, typeName);
        }
    }
}
