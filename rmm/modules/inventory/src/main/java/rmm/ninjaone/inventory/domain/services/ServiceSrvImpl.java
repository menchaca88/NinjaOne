package rmm.ninjaone.inventory.domain.services;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.inventory.domain.contracts.clients.ClientRepository;
import rmm.ninjaone.inventory.domain.contracts.services.ServiceRepository;
import rmm.ninjaone.inventory.domain.contracts.services.ServiceSrv;
import rmm.ninjaone.inventory.domain.exceptions.ClientNotFoundException;
import rmm.ninjaone.inventory.domain.exceptions.ServiceAlreadyExistsException;
import rmm.ninjaone.inventory.domain.exceptions.ServiceNotFoundException;
import rmm.ninjaone.inventory.domain.factories.ServiceFactory;
import rmm.ninjaone.inventory.domain.models.Service;
import rmm.ninjaone.inventory.domain.specifications.ClientSpecifications;
import rmm.ninjaone.inventory.domain.specifications.ServiceSpecifications;

@org.springframework.stereotype.Service("ServiceSrvImpl")
@RequiredArgsConstructor
public class ServiceSrvImpl implements ServiceSrv {
    private final ClientRepository clientRepository;
    private final ServiceRepository serviceRepository;
    private final ServiceFactory serviceFactory;

    @Override
    public List<Service> getAll(UUID clientId) {
        return serviceRepository.findAll(ServiceSpecifications.findByClientId(clientId));
    }

    @Override
    public Service create(UUID clientId, UUID typeId) {
        var client = clientRepository
            .findOne(ClientSpecifications.findById(clientId))
            .orElseThrow(() -> new ClientNotFoundException(clientId));

        if (serviceRepository.exists(ServiceSpecifications.findByTypeAndClientId(typeId, clientId)))
            throw new ServiceAlreadyExistsException(typeId);

        var service = serviceFactory
            .ofType(typeId)
            .ownedBy(clientId)
            .build();

        client.addService(service);

        service = serviceRepository.save(service);
        clientRepository.save(client);

        return service;
    }

    @Override
    public Service delete(UUID id) {
        var service = serviceRepository
            .findOne(ServiceSpecifications.findById(id))
            .orElseThrow(() -> new ServiceNotFoundException(id));

        serviceRepository.delete(service);

        return service;
    }

    @Override
    public int deleteType(UUID typeId) {
        var services = serviceRepository.findAll(ServiceSpecifications.findByTypeId(typeId));

        for (var service : services)
            serviceRepository.delete(service);

        return services.size();
    }

    @Override
    public int renameType(UUID typeId, String name) {
        var services = serviceRepository.findAll(ServiceSpecifications.findByTypeId(typeId));

        for (var service : services) {
            service.setTypeName(name);
            serviceRepository.save(service);
        }

        return services.size();
    }
}
