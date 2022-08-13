package rmm.ninjaone.catalog.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

import java.util.Collections;
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
import rmm.ninjaone.catalog.data.DeviceMother;
import rmm.ninjaone.catalog.data.SubscriptionMother;
import rmm.ninjaone.catalog.domain.contracts.devices.DeviceRepository;
import rmm.ninjaone.catalog.domain.contracts.services.ServiceRepository;
import rmm.ninjaone.catalog.domain.exceptions.DeviceAlreadyExistsException;
import rmm.ninjaone.catalog.domain.exceptions.DeviceNotFoundException;
import rmm.ninjaone.catalog.domain.exceptions.NameAlreadyUsedException;
import rmm.ninjaone.catalog.domain.models.devices.DeviceType;
import rmm.ninjaone.catalog.domain.services.DeviceSrvImpl;

@ExtendWith(MockitoExtension.class)
public class DeviceSrvImplTests {
    @Mock
    DeviceRepository deviceRepository;

    @Mock
    ServiceRepository serviceRepository;

    DeviceSrvImpl srvImpl;

    @BeforeEach
    void setUp() {
        Mockito.reset(deviceRepository);
        Mockito.reset(serviceRepository);

        Mockito.lenient().when(deviceRepository.save(any())).thenAnswer(x -> x.getArgument(0));

        srvImpl = new DeviceSrvImpl(serviceRepository, deviceRepository);
    }

    @Test
    @SuppressWarnings("unchecked")
    void get_WithExistingId_ReturnsDeviceDetails() {
        // Arrange
        var device = DeviceMother.random();

        Mockito
            .when(deviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(device));

        // Act
        var result = srvImpl.get(device.getId());

        // Assert
        assertEquals(device.getId(), result.getId());
        assertEquals(device.getName(), result.getName());
        assertEquals(device.getSku(), result.getSku());
    }

    @Test
    @SuppressWarnings("unchecked")
    void get_WithExistingSku_ReturnsDeviceDetails() {
        // Arrange
        var device = DeviceMother.random();

        Mockito
            .when(deviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(device));

        // Act
        var result = srvImpl.get(device.getSku());

        // Assert
        assertEquals(device.getId(), result.getId());
        assertEquals(device.getName(), result.getName());
        assertEquals(device.getSku(), result.getSku());
    }

    @Test
    void getAll_DevicesInRepo_ReturnsDevicesList() {
        // Arrange
        var devices = DeviceMother.count(5);
        
        Mockito
            .when(deviceRepository.findAll())
            .thenReturn(devices);

        // Act
        var result = srvImpl.getAll();

        // Assert
        assertEquals(devices.size(), result.size());
        for (int i = 0; i < devices.size(); i++) {
            assertEquals(devices.get(i).getId(), result.get(i).getId());
            assertEquals(devices.get(i).getName(), result.get(i).getName());
            assertEquals(devices.get(i).getSku(), result.get(i).getSku());
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithValidName_IsSavedToRepo() {
        // Arrange
        var name = StringMother.random();
        var subscription = SubscriptionMother.deviceRandom();
        
        Mockito
            .when(deviceRepository.exists(any(Specification.class)))
            .thenReturn(false);

        // Act
        srvImpl.create(name, subscription);

        // Assert
        Mockito.verify(deviceRepository).save(argThat((DeviceType d) ->
            d.getName().equals(name)));
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithValidName_ReturnsDeviceDetails() {
        // Arrange
        var name = StringMother.random();
        var subscription = SubscriptionMother.deviceRandom();
        
        Mockito
            .when(deviceRepository.exists(any(Specification.class)))
            .thenReturn(false);

        // Act
        var device = srvImpl.create(name, subscription);

        // Assert
        assertEquals(name, device.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithExistingName_ThrowsException() {
        // Arrange
        var name = StringMother.random();
        var subscription = SubscriptionMother.deviceRandom();

        Mockito
            .when(deviceRepository.exists(any(Specification.class)))
            .thenReturn(true);

        // Act
        Executable handleAct = () -> srvImpl.create(name, subscription);

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

        Mockito
            .when(serviceRepository.findAll())
            .thenReturn(Collections.emptyList());

        // Act
        srvImpl.delete(device.getId());

        // Assert
        Mockito.verify(deviceRepository).delete(device);
    }

    @Test
    @SuppressWarnings("unchecked")
    void delete_WithExistingDevice_ReturnsDeviceDetails() {
        // Arrange
        var device = DeviceMother.random();

        Mockito
            .when(deviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(device));

        Mockito
            .when(serviceRepository.findAll())
            .thenReturn(Collections.emptyList());

        // Act
        var result = srvImpl.delete(device.getId());

        // Assert
        assertEquals(device.getId(), result.getId());
        assertEquals(device.getName(), result.getName());
        assertEquals(device.getSku(), result.getSku());
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
        var name = StringMother.random();
        var device = DeviceMother.random();

        Mockito
            .when(deviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(device));

        // Act
        srvImpl.update(device.getId(), name);

        // Assert
        Mockito.verify(deviceRepository).save(argThat((DeviceType d) ->
            d.getId().equals(device.getId()) &&
            d.getName().equals(name)));
    }

    @Test
    @SuppressWarnings("unchecked")
    void update_WithExistingDevice_ReturnsDeviceDetails() {
        // Arrange
        var name = StringMother.random();
        var device = DeviceMother.random();

        Mockito
            .when(deviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(device));

        // Act
        var result = srvImpl.update(device.getId(), name);

        // Assert
        assertEquals(device.getId(), result.getId());
        assertEquals(name, result.getName());
        assertEquals(device.getSku(), result.getSku());
    }

    @Test
    @SuppressWarnings("unchecked")
    void update_WithNonExistingDevice_ThrowsException() {
        // Arrange
        var name = StringMother.random();
        var deviceId = UUID.randomUUID();

        Mockito
            .when(deviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.empty());

        // Act
        Executable updateAct = () -> srvImpl.update(deviceId, name);

        // Assert
        assertThrows(DeviceNotFoundException.class, updateAct);
    }

    @Test
    @SuppressWarnings("unchecked")
    void update_WithExistingName_ThrowsException() {
        // Arrange
        var name = StringMother.random();
        var device = DeviceMother.random();
        
        Mockito
            .when(deviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(device));

        Mockito
            .when(deviceRepository.exists(any(Specification.class)))
            .thenReturn(true);

        // Act
        Executable updateAct = () -> srvImpl.update(device.getId(), name);

        // Assert
        assertThrows(NameAlreadyUsedException.class, updateAct);
    }
}
