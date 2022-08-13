package rmm.ninjaone.inventory.domain.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.inventory.domain.contracts.clients.ClientRepository;
import rmm.ninjaone.inventory.domain.contracts.clients.ClientSrv;
import rmm.ninjaone.inventory.domain.contracts.devices.DeviceRepository;
import rmm.ninjaone.inventory.domain.contracts.services.ServiceRepository;
import rmm.ninjaone.inventory.domain.exceptions.ClientAlreadyExistsException;
import rmm.ninjaone.inventory.domain.exceptions.ClientNotFoundException;
import rmm.ninjaone.inventory.domain.models.Client;
import rmm.ninjaone.inventory.domain.specifications.ClientSpecifications;

@Service
@RequiredArgsConstructor
public class ClientSrvImpl implements ClientSrv {
    private final DeviceRepository deviceRepository;
    private final ServiceRepository serviceRepository;
    private final ClientRepository clientRepository;

    @Override
    public Client get(UUID id) {
        var client = clientRepository
            .findOne(ClientSpecifications.findById(id))
            .orElseThrow(() -> new ClientNotFoundException(id));

        return client;
    }

    @Override
    public Client create(UUID id, String name) {
        if (clientRepository.exists(ClientSpecifications.findById(id)))
            throw new ClientAlreadyExistsException(id);

        var client = Client.create(id, name);

        client = clientRepository.save(client);

        return client;
    }

    @Override
    public Client update(UUID id, String name) {
        var client = clientRepository
            .findOne(ClientSpecifications.findById(id))
            .orElseThrow(() -> new ClientNotFoundException(id));

        client.rename(name);

        client = clientRepository.save(client);

        return client;
    }

    @Override
    public Client delete(UUID id) {
        var client = clientRepository
            .findOne(ClientSpecifications.findById(id))
            .orElseThrow(() -> new ClientNotFoundException(id));

        for (var device : client.getDevices())
            deviceRepository.delete(device);

        for (var service : client.getServices())
            serviceRepository.delete(service);

        clientRepository.delete(client);

        return client;
    }
}
