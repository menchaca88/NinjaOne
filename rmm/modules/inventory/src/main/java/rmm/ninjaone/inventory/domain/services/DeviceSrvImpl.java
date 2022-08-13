package rmm.ninjaone.inventory.domain.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.inventory.domain.contracts.clients.ClientRepository;
import rmm.ninjaone.inventory.domain.contracts.devices.DeviceRepository;
import rmm.ninjaone.inventory.domain.contracts.devices.DeviceSrv;
import rmm.ninjaone.inventory.domain.exceptions.ClientNotFoundException;
import rmm.ninjaone.inventory.domain.exceptions.DeviceAlreadyExistsException;
import rmm.ninjaone.inventory.domain.exceptions.DeviceNotFoundException;
import rmm.ninjaone.inventory.domain.factories.DeviceFactory;
import rmm.ninjaone.inventory.domain.models.Device;
import rmm.ninjaone.inventory.domain.specifications.ClientSpecifications;
import rmm.ninjaone.inventory.domain.specifications.DeviceSpecifications;

@Service("DeviceSrvImpl")
@RequiredArgsConstructor
public class DeviceSrvImpl implements DeviceSrv {
    private final ClientRepository clientRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceFactory deviceFactory;

    @Override
    public List<Device> getAll(UUID clientId) {
        return deviceRepository.findAll(DeviceSpecifications.findByClientId(clientId));
    }

    @Override
    public Device create(UUID clientId, int count, UUID typeId) {
        var client = clientRepository
            .findOne(ClientSpecifications.findById(clientId))
            .orElseThrow(() -> new ClientNotFoundException(clientId));

        if (deviceRepository.exists(DeviceSpecifications.findByTypeAndClientId(typeId, clientId)))
            throw new DeviceAlreadyExistsException(typeId);

        var device = deviceFactory
            .ofType(typeId)
            .withCount(count)
            .ownedBy(clientId)
            .build();

        client.addDevice(device);

        device = deviceRepository.save(device);
        clientRepository.save(client);

        return device;
    }

    @Override
    public Device update(UUID id, int count) {
        var device = deviceRepository
            .findOne(DeviceSpecifications.findById(id))
            .orElseThrow(() -> new DeviceNotFoundException(id));

        device.updateCount(count);

        device = deviceRepository.save(device);

        return device;
    }

    @Override
    public Device delete(UUID id) {
        var device = deviceRepository
            .findOne(DeviceSpecifications.findById(id))
            .orElseThrow(() -> new DeviceNotFoundException(id));

        deviceRepository.delete(device);

        return device;
    }

    @Override
    public int deleteType(UUID typeId) {
        var devices = deviceRepository.findAll(DeviceSpecifications.findByTypeId(typeId));

        for (var device : devices)
            deviceRepository.delete(device);

        return devices.size();
    }

    @Override
    public int renameType(UUID typeId, String name) {
        var devices = deviceRepository.findAll(DeviceSpecifications.findByTypeId(typeId));

        for (var device : devices) {
            device.setTypeName(name);
            deviceRepository.save(device);
        }

        return devices.size();
    }
}
