package rmm.ninjaone.catalog.domain.services;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;
import rmm.ninjaone.catalog.domain.contracts.devices.DeviceRepository;
import rmm.ninjaone.catalog.domain.contracts.services.ServiceRepository;
import rmm.ninjaone.catalog.domain.contracts.services.ServiceSrv;
import rmm.ninjaone.catalog.domain.exceptions.DeviceNotFoundException;
import rmm.ninjaone.catalog.domain.exceptions.NameAlreadyUsedException;
import rmm.ninjaone.catalog.domain.exceptions.ServiceAlreadyExistsException;
import rmm.ninjaone.catalog.domain.exceptions.ServiceNotFoundException;
import rmm.ninjaone.catalog.domain.models.services.ServiceType;
import rmm.ninjaone.catalog.domain.models.services.subscriptions.ServiceSubscription;
import rmm.ninjaone.catalog.domain.specifications.DeviceSpecifications;
import rmm.ninjaone.catalog.domain.specifications.ServiceSpecifications;

@org.springframework.stereotype.Service("ServiceTypeSrvImpl")
@RequiredArgsConstructor
public class ServiceSrvImpl implements ServiceSrv {
    private final DeviceRepository deviceRepository;
    private final ServiceRepository serviceRepository;

    public ServiceType get(UUID id) {
        var service = serviceRepository
            .findOne(ServiceSpecifications.findById(id))
            .orElseThrow(() -> new ServiceNotFoundException(id));

        return service;
    }

    public ServiceType get(Sku sku) {
        var service = serviceRepository
                .findOne(ServiceSpecifications.findBySku(sku))
                .orElseThrow(() -> new ServiceNotFoundException(sku.getRaw()));

        return service;
    }

    public List<ServiceType> getAll() {
        return serviceRepository.findAll();
    }

    public ServiceType create(String name, ServiceSubscription subscription) {
        if (serviceRepository.exists(ServiceSpecifications.findByName(name)))
            throw new ServiceAlreadyExistsException(name);

        for (var id : subscription.getRelatedDevices())
            if (!deviceRepository.exists(DeviceSpecifications.findById(id)))
                throw new DeviceNotFoundException(id);

        var service = ServiceType.create(name, subscription);

        service = serviceRepository.save(service);

        return service;
    }

    @Override
    public ServiceType update(UUID id, String name) {
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
    public ServiceType delete(UUID id) {
        var service = serviceRepository
            .findOne(ServiceSpecifications.findById(id))
            .orElseThrow(() -> new ServiceNotFoundException(id));

        serviceRepository.delete(service);

        return service;
    }
}
