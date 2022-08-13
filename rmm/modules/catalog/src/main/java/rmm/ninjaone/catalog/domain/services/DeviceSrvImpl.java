package rmm.ninjaone.catalog.domain.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Sku;
import rmm.ninjaone.catalog.domain.contracts.devices.DeviceRepository;
import rmm.ninjaone.catalog.domain.contracts.devices.DeviceSrv;
import rmm.ninjaone.catalog.domain.contracts.services.ServiceRepository;
import rmm.ninjaone.catalog.domain.exceptions.DeviceAlreadyExistsException;
import rmm.ninjaone.catalog.domain.exceptions.DeviceNotFoundException;
import rmm.ninjaone.catalog.domain.exceptions.DeviceUsedByServiceException;
import rmm.ninjaone.catalog.domain.exceptions.NameAlreadyUsedException;
import rmm.ninjaone.catalog.domain.models.devices.DeviceType;
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.DeviceSubscription;
import rmm.ninjaone.catalog.domain.specifications.DeviceSpecifications;

@Service("DeviceTypeSrvImpl")
@RequiredArgsConstructor
public class DeviceSrvImpl implements DeviceSrv {
    private final ServiceRepository serviceRepository;
    private final DeviceRepository deviceRepository;

    public DeviceType get(UUID id) {
        var device = deviceRepository
            .findOne(DeviceSpecifications.findById(id))
            .orElseThrow(() -> new DeviceNotFoundException(id));

        return device;
    }

    public DeviceType get(Sku sku) {
        var device = deviceRepository
                .findOne(DeviceSpecifications.findBySku(sku))
                .orElseThrow(() -> new DeviceNotFoundException(sku.getRaw()));

        return device;
    }

    public List<DeviceType> getAll() {
        return deviceRepository.findAll();
    }

    public DeviceType create(String name, DeviceSubscription subscription) {
        if (deviceRepository.exists(DeviceSpecifications.findByName(name)))
            throw new DeviceAlreadyExistsException(name);

        var device = DeviceType.create(name, subscription);

        device = deviceRepository.save(device);

        return device;
    }

    @Override
    public DeviceType update(UUID id, String name) {
        var device = deviceRepository
            .findOne(DeviceSpecifications.findById(id))
            .orElseThrow(() -> new DeviceNotFoundException(id));

        if (deviceRepository.exists(DeviceSpecifications.findByNameExcept(name, id)))
            throw new NameAlreadyUsedException(name);

        device.rename(name);

        device = deviceRepository.save(device);

        return device;
    }

    @Override
    public DeviceType delete(UUID id) {
        var device = deviceRepository
            .findOne(DeviceSpecifications.findById(id))
            .orElseThrow(() -> new DeviceNotFoundException(id));

        var services = serviceRepository.findAll();
        if (services.stream().anyMatch(s -> s.getSubscription().relatedTo(id)))
            throw new DeviceUsedByServiceException(id);

        deviceRepository.delete(device);

        return device;
    }
}
