package rmm.ninjaone.inventory.domain.contracts.clients;

import java.util.UUID;

import rmm.ninjaone.inventory.domain.models.Client;

public interface ClientSrv {
    Client get(UUID id);
    Client create(UUID id, String name);
    Client update(UUID id, String name);
    Client delete(UUID id);
}
