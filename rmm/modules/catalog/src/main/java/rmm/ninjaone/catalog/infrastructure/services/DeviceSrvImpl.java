package rmm.ninjaone.catalog.infrastructure.services;

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
import rmm.ninjaone.catalog.domain.models.devices.Device;
import rmm.ninjaone.catalog.domain.models.devices.subscriptions.DeviceSubscription;
import rmm.ninjaone.catalog.domain.specifications.DeviceSpecifications;

@Service
@RequiredArgsConstructor
public class DeviceSrvImpl implements DeviceSrv {
    private final ServiceRepository serviceRepository;
    private final DeviceRepository deviceRepository;

    public Device get(UUID id) {
        var device = deviceRepository
            .findOne(DeviceSpecifications.findById(id))
            .orElseThrow(() -> new DeviceNotFoundException(id));

        return device;
    }

    public Device get(Sku sku) {
        var device = deviceRepository
                .findOne(DeviceSpecifications.findBySku(sku))
                .orElseThrow(() -> new DeviceNotFoundException(sku.getRaw()));

        return device;
    }

    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    public Device create(String name, DeviceSubscription subscription) {
        if (deviceRepository.exists(DeviceSpecifications.findByName(name)))
            throw new DeviceAlreadyExistsException(name);

        var device = Device.create(name, subscription);

        device = deviceRepository.save(device);

        return device;
    }

    @Override
    public Device update(UUID id, String name) {
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
    public Device delete(UUID id) {
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
