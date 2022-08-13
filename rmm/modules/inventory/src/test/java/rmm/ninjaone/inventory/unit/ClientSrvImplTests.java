package rmm.ninjaone.inventory.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.inventory.data.ClientMother;
import rmm.ninjaone.inventory.domain.contracts.clients.ClientRepository;
import rmm.ninjaone.inventory.domain.contracts.devices.DeviceRepository;
import rmm.ninjaone.inventory.domain.contracts.services.ServiceRepository;
import rmm.ninjaone.inventory.domain.exceptions.ClientAlreadyExistsException;
import rmm.ninjaone.inventory.domain.exceptions.ClientNotFoundException;
import rmm.ninjaone.inventory.domain.models.Client;
import rmm.ninjaone.inventory.domain.services.ClientSrvImpl;

@ExtendWith(MockitoExtension.class)
public class ClientSrvImplTests {
    @Mock
    DeviceRepository deviceRepository;

    @Mock
    ServiceRepository serviceRepository;

    @Mock
    ClientRepository clientRepository;

    ClientSrvImpl srvImpl;

    @BeforeEach
    void setUp() {
        Mockito.reset(deviceRepository);
        Mockito.reset(serviceRepository);
        Mockito.reset(clientRepository);

        Mockito.lenient().when(clientRepository.save(any())).thenAnswer(x -> x.getArgument(0));

        srvImpl = new ClientSrvImpl(deviceRepository, serviceRepository, clientRepository);
    }

    @Test
    @SuppressWarnings("unchecked")
    void get_WithExistingId_ReturnsClientDetails() {
        // Arrange
        var client = ClientMother.random();

        Mockito
            .when(clientRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(client));

        // Act
        var result = srvImpl.get(client.getId());

        // Assert
        assertEquals(client.getId(), result.getId());
        assertEquals(client.getName(), result.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithValidArgs_IsSavedToRepo() {
        // Arrange
        var name = StringMother.random();
        var id = UUID.randomUUID();
        
        Mockito
            .when(clientRepository.exists(any(Specification.class)))
            .thenReturn(false);

        // Act
        srvImpl.create(id, name);

        // Assert
        Mockito.verify(clientRepository).save(argThat((Client c) ->
            c.getId().equals(id) &&
            c.getName().equals(name)));
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithValidArgs_ReturnsDetails() {
        // Arrange
        var name = StringMother.random();
        var id = UUID.randomUUID();
        
        Mockito
            .when(clientRepository.exists(any(Specification.class)))
            .thenReturn(false);

        // Act
        var client = srvImpl.create(id, name);

        // Assert
        assertEquals(id, client.getId());
        assertEquals(name, client.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithExistingId_ThrowsException() {
        // Arrange
        var name = StringMother.random();
        var id = UUID.randomUUID();

        Mockito
            .when(clientRepository.exists(any(Specification.class)))
            .thenReturn(true);

        // Act
        Executable handleAct = () -> srvImpl.create(id, name);

        // Assert
        assertThrows(ClientAlreadyExistsException.class, handleAct);
    }

    @Test
    @SuppressWarnings("unchecked")
    void delete_WithExistingClient_IsDeletedFromRepo() {
        // Arrange
        var client = ClientMother.random();

        Mockito
            .when(clientRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(client));

        // Act
        srvImpl.delete(client.getId());

        // Assert
        Mockito.verify(clientRepository).delete(client);
    }

    @Test
    @SuppressWarnings("unchecked")
    void delete_WithExistingClient_ReturnsDetails() {
        // Arrange
        var client = ClientMother.random();

        Mockito
            .when(clientRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(client));

        // Act
        var result = srvImpl.delete(client.getId());

        // Assert
        assertEquals(client.getId(), result.getId());
        assertEquals(client.getName(), result.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void delete_WithNonExistingClient_ThrowsException() {
        // Arrange
        var clientId = UUID.randomUUID();

        Mockito
            .when(clientRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.empty());

        // Act
        Executable deleteAct = () -> srvImpl.delete(clientId);

        // Assert
        assertThrows(ClientNotFoundException.class, deleteAct);
    }

    @Test
    @SuppressWarnings("unchecked")
    void update_WithExistingClient_IsSavedToRepo() {
        // Arrange
        var name = StringMother.random();
        var client = ClientMother.random();

        Mockito
            .when(clientRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(client));

        // Act
        srvImpl.update(client.getId(), name);

        // Assert
        Mockito.verify(clientRepository).save(argThat((Client c) ->
            c.getId().equals(client.getId()) &&
            c.getName().equals(name)));
    }

    @Test
    @SuppressWarnings("unchecked")
    void update_WithExistingClient_ReturnsDetails() {
        // Arrange
        var name = StringMother.random();
        var client = ClientMother.random();

        Mockito
            .when(clientRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(client));

        // Act
        var result = srvImpl.update(client.getId(), name);

        // Assert
        assertEquals(client.getId(), result.getId());
        assertEquals(name, result.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void update_WithNonExistingClient_ThrowsException() {
        // Arrange
        var name = StringMother.random();
        var id = UUID.randomUUID();

        Mockito
            .when(clientRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.empty());

        // Act
        Executable updateAct = () -> srvImpl.update(id, name);

        // Assert
        assertThrows(ClientNotFoundException.class, updateAct);
    }
}
