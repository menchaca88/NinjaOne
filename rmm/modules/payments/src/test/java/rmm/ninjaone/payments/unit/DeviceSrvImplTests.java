package rmm.ninjaone.payments.unit;

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

import rmm.ninjaone.payments.data.PayerMother;
import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.payments.data.DeviceMother;
import rmm.ninjaone.payments.domain.contracts.payers.PayerRepository;
import rmm.ninjaone.payments.domain.contracts.devices.DeviceRepository;
import rmm.ninjaone.payments.domain.exceptions.DeviceAlreadyExistsException;
import rmm.ninjaone.payments.domain.exceptions.DeviceNotFoundException;
import rmm.ninjaone.payments.domain.models.DeviceCharge;
import rmm.ninjaone.payments.domain.services.DeviceSrvImpl;

@ExtendWith(MockitoExtension.class)
public class DeviceSrvImplTests {
    @Mock
    DeviceRepository deviceRepository;

    @Mock
    PayerRepository payerRepository;

    DeviceSrvImpl srvImpl;

    @BeforeEach
    void setUp() {
        Mockito.reset(deviceRepository);
        Mockito.reset(payerRepository);

        Mockito.lenient().when(deviceRepository.save(any())).thenAnswer(x -> x.getArgument(0));

        srvImpl = new DeviceSrvImpl(payerRepository, deviceRepository);
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithValidArgs_IsSavedToRepo() {
        // Arrange
        var id = UUID.randomUUID();
        var typeId = UUID.randomUUID();
        var typeName = StringMother.random();
        var payer = PayerMother.random();
        var count = 3;
        
        Mockito
            .when(payerRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(payer));

        // Act
        srvImpl.create(id, payer.getId(), count, typeId, typeName);

        // Assert
        Mockito.verify(deviceRepository).save(argThat((DeviceCharge d) ->
            d.getId().equals(id) &&
            d.getTypeId().equals(typeId) &&
            d.getTypeName().equals(typeName) &&
            d.getPayerId().equals(payer.getId()) &&
            d.getCount() == count));
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithValidArgs_ReturnsDetails() {
        // Arrange
        var id = UUID.randomUUID();
        var typeId = UUID.randomUUID();
        var typeName = StringMother.random();
        var payer = PayerMother.random();
        var count = 3;
        
        Mockito
            .when(payerRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(payer));

        // Act
        var result = srvImpl.create(id, payer.getId(), count, typeId, typeName);

        // Assert
        assertEquals(id, result.getId());
        assertEquals(typeId, result.getTypeId());
        assertEquals(typeName, result.getTypeName());
        assertEquals(payer.getId(), result.getPayerId());
        assertEquals(count, result.getCount());
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithExistingId_ThrowsException() {
        // Arrange
        var id = UUID.randomUUID();
        var typeId = UUID.randomUUID();
        var typeName = StringMother.random();
        var payer = PayerMother.random();
        var count = 3;

        Mockito
            .when(deviceRepository.exists(any(Specification.class)))
            .thenReturn(true);

        Mockito
            .when(payerRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(payer));

        // Act
        Executable handleAct = () -> srvImpl.create(id, payer.getId(), count, typeId, typeName);

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
        Mockito.verify(deviceRepository).save(argThat((DeviceCharge d) ->
            d.getId().equals(device.getId()) &&
            d.getCount() == count));
    }

    @Test
    @SuppressWarnings("unchecked")
    void update_WithExistingDevice_ReturnsDetails() {
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
    void update_WithNonExistingDevice_ThrowsException() {
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
