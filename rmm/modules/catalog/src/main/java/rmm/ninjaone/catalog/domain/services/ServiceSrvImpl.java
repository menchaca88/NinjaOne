package rmm.ninjaone.catalog.domain.services;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;
import rmm.ninjaone.catalog.domain.contracts.ServiceRepository;
import rmm.ninjaone.catalog.domain.contracts.ServiceSrv;
import rmm.ninjaone.catalog.domain.exceptions.NameAlreadyUsedException;
import rmm.ninjaone.catalog.domain.exceptions.ServiceAlreadyExistsException;
import rmm.ninjaone.catalog.domain.exceptions.ServiceNotFoundException;
import rmm.ninjaone.catalog.domain.models.services.Service;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.ServiceSubscription;
import rmm.ninjaone.catalog.domain.specifications.ServiceSpecifications;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceSrvImpl implements ServiceSrv {
    private final ServiceRepository serviceRepository;

    public Service get(UUID id) {
        var service = serviceRepository
            .findOne(ServiceSpecifications.findById(id))
            .orElseThrow(() -> new ServiceNotFoundException(id));

        return service;
    }

    public Service get(Sku sku) {
        var service = serviceRepository
                .findOne(ServiceSpecifications.findBySku(sku))
                .orElseThrow(() -> new ServiceNotFoundException(sku.getRaw()));

        return service;
    }

    public List<Service> getAll() {
        return serviceRepository.findAll();
    }

    public Service create(String name, ServiceSubscription subscription) {
        if (serviceRepository.exists(ServiceSpecifications.findByName(name)))
            throw new ServiceAlreadyExistsException(name);

        var service = Service.create(name, subscription);

        service = serviceRepository.save(service);

        return service;
    }

    @Override
    public Service update(UUID id, String name) {
        var service = serviceRepository
            .findOne(ServiceSpecifications.findById(id))
            .orElseThrow(() -> new ServiceNotFoundException(id));

        if (serviceRepository.exists(ServiceSpecifications.findByNameExcept(name, id)))
            throw new NameAlreadyUsedException(name);

        service.rename(name);

        service = serviceRepository.save(service);

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
}
