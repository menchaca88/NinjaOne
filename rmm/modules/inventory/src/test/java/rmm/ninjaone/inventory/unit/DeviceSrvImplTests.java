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

import rmm.ninjaone.buildingblocks.ports.catalog.CatalogPort;
import rmm.ninjaone.inventory.data.ClientMother;
import rmm.ninjaone.inventory.data.DeviceMother;
import rmm.ninjaone.inventory.domain.contracts.clients.ClientRepository;
import rmm.ninjaone.inventory.domain.contracts.devices.DeviceRepository;
import rmm.ninjaone.inventory.domain.exceptions.DeviceAlreadyExistsException;
import rmm.ninjaone.inventory.domain.exceptions.DeviceNotFoundException;
import rmm.ninjaone.inventory.domain.factories.DeviceFactory;
import rmm.ninjaone.inventory.domain.models.Device;
import rmm.ninjaone.inventory.domain.services.DeviceSrvImpl;

@ExtendWith(MockitoExtension.class)
public class DeviceSrvImplTests {
    @Mock
    DeviceRepository deviceRepository;

    @Mock
    ClientRepository clientRepository;

    @Mock
    CatalogPort catalogPort;

    DeviceSrvImpl srvImpl;

    @BeforeEach
    void setUp() {
        Mockito.reset(deviceRepository);
        Mockito.reset(clientRepository);
        Mockito.reset(catalogPort);

        Mockito.lenient().when(deviceRepository.save(any())).thenAnswer(x -> x.getArgument(0));

        var factory = new DeviceFactory(catalogPort);
        srvImpl = new DeviceSrvImpl(clientRepository, deviceRepository, factory);
    }

    @Test
    @SuppressWarnings("unchecked")
    void getAll_DevicesInRepo_ReturnsDevicesList() {
        // Arrange
        var clientId = UUID.randomUUID();
        var devices = DeviceMother.countWithClient(clientId, 5);
        
        Mockito
            .when(deviceRepository.findAll(any(Specification.class)))
            .thenReturn(devices);

        // Act
        var result = srvImpl.getAll(clientId);

        // Assert
        assertEquals(devices.size(), result.size());
        for (int i = 0; i < devices.size(); i++) {
            assertEquals(devices.get(i).getId(), result.get(i).getId());
            assertEquals(devices.get(i).getCount(), result.get(i).getCount());
            assertEquals(clientId, result.get(i).getClientId());
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithValidArgs_IsSavedToRepo() {
        // Arrange
        var client = ClientMother.random();
        var type = DeviceMother.randomType();
        var count = 3;
        
        Mockito
            .when(clientRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(client));

        Mockito
            .when(catalogPort.findDevice(type.getId()))
            .thenReturn(type);

        // Act
        srvImpl.create(client.getId(), count, type.getId());

        // Assert
        Mockito.verify(deviceRepository).save(argThat((Device d) ->
            d.getClientId().equals(client.getId()) &&
            d.getCount() == count &&
            d.getTypeId().equals(type.getId()) &&
            d.getTypeName().equals(type.getName())));
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithValidArgs_ReturnsDetails() {
        // Arrange
        var client = ClientMother.random();
        var type = DeviceMother.randomType();
        var count = 3;
        
        Mockito
            .when(clientRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(client));

        Mockito
            .when(catalogPort.findDevice(type.getId()))
            .thenReturn(type);

        // Act
        var result = srvImpl.create(client.getId(), count, type.getId());

        // Assert
        assertEquals(client.getId(), result.getClientId());
        assertEquals(count, result.getCount());
        assertEquals(type.getId(), result.getTypeId());
        assertEquals(type.getName(), result.getTypeName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithExistingTypeAndClient_ThrowsException() {
        // Arrange
        var client = ClientMother.random();
        var typeId = UUID.randomUUID();
        var count = 3;

        Mockito
            .when(deviceRepository.exists(any(Specification.class)))
            .thenReturn(true);

        Mockito
            .when(clientRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(client));

        // Act
        Executable handleAct = () -> srvImpl.create(client.getId(), count, typeId);

        // Assert
        assertThrows(DeviceAlreadyExistsException.class, handleAct);
    }

    @Test
    @SuppressWarnings("unchecked")
    void delete_WithExistingDevice_IsDeletedFromRepo() {
        // Arrange
        var device = DeviceMother.random();

        Mockito
            .when(deviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(device));

        // Act
        srvImpl.delete(device.getId());

        // Assert
        Mockito.verify(deviceRepository).delete(device);
    }

    @Test
    @SuppressWarnings("unchecked")
    void delete_WithExistingDevice_ReturnsDetails() {
        // Arrange
        var device = DeviceMother.random();

        Mockito
            .when(deviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(device));

        // Act
        var result = srvImpl.delete(device.getId());

        // Assert
        assertEquals(device.getId(), result.getId());
        assertEquals(device.getCount(), result.getCount());
        assertEquals(device.getTypeId(), result.getTypeId());
        assertEquals(device.getTypeName(), result.getTypeName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void delete_WithNonExistingDevice_ThrowsException() {
        // Arrange
        var deviceId = UUID.randomUUID();

        Mockito
            .when(deviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.empty());

        // Act
        Executable deleteAct = () -> srvImpl.delete(deviceId);

        // Assert
        assertThrows(DeviceNotFoundException.class, deleteAct);
    }

    @Test
    @SuppressWarnings("unchecked")
    void update_WithExistingDevice_IsSavedToRepo() {
        // Arrange
        var count = 5;
        var device = DeviceMother.random();

        Mockito
            .when(deviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(device));

        // Act
        srvImpl.update(device.getId(), count);

        // Assert
        Mockito.verify(deviceRepository).save(argThat((Device d) ->
            d.getId().equals(device.getId()) &&
            d.getCount() == count));
    }

    @Test
    @SuppressWarnings("unchecked")
    void update_WithExistingClient_ReturnsDetails() {
        // Arrange
        var count = 5;
        var device = DeviceMother.random();

        Mockito
            .when(deviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(device));

        // Act
        var result = srvImpl.update(device.getId(), count);

        // Assert
        assertEquals(device.getId(), result.getId());
        assertEquals(count, result.getCount());
    }

    @Test
    @SuppressWarnings("unchecked")
    void update_WithNonExistingClient_ThrowsException() {
        // Arrange
        var count = 5;
        var id = UUID.randomUUID();

        Mockito
            .when(deviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.empty());

        // Act
        Executable updateAct = () -> srvImpl.update(id, count);

        // Assert
        assertThrows(DeviceNotFoundException.class, updateAct);
    }
}
