package rmm.ninjaone.inventory.data;

import java.util.UUID;

import rmm.ninjaone.buildingblocks.data.ObjectMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.inventory.domain.models.Client;

public class ClientMother extends ObjectMother {
    public static Client random() {
        var client = Client.create(UUID.randomUUID(), StringMother.random());

        return client;
    }
}