package rmm.ninjaone.inventory.domain.factories;

import java.util.UUID;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.ports.catalog.CatalogPort;
import rmm.ninjaone.inventory.domain.exceptions.ServiceTypeNotFoundException;
import rmm.ninjaone.inventory.domain.models.Service;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceFactory {
    private final CatalogPort catalog;

    public ServiceBuilder ofType(UUID typeId) {
        var type = catalog.findService(typeId);
        if (type == null)
            throw new ServiceTypeNotFoundException(typeId);

        var builder = new ServiceBuilder();
        return builder
            .ofType(type.getId())
            .typeName(type.getName());
    }
    
    public static class ServiceBuilder {
        private UUID typeId;
        private String typeName;
        private UUID clientId;

        private ServiceBuilder ofType(@NonNull UUID typeId) {
            this.typeId = typeId;
            return this;
        }

        private ServiceBuilder typeName(@NonNull String typeName) {
            this.typeName = typeName;
            return this;
        }

        public ServiceBuilder ownedBy(@NonNull UUID clientId) {
            this.clientId = clientId;
            return this;
        }

        public Service build() {
            return Service.create(clientId, typeId, typeName);
        }
    }
}
