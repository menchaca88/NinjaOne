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

import rmm.ninjaone.buildingblocks.data.StringMother;
import rmm.ninjaone.payments.data.PayerMother;
import rmm.ninjaone.payments.domain.contracts.payers.PayerRepository;
import rmm.ninjaone.payments.domain.contracts.devices.DeviceRepository;
import rmm.ninjaone.payments.domain.contracts.services.ServiceRepository;
import rmm.ninjaone.payments.domain.exceptions.PayerAlreadyExistsException;
import rmm.ninjaone.payments.domain.exceptions.PayerNotFoundException;
import rmm.ninjaone.payments.domain.models.Payer;
import rmm.ninjaone.payments.domain.services.PayerSrvImpl;

@ExtendWith(MockitoExtension.class)
public class PayerSrvImplTests {
    @Mock
    DeviceRepository deviceRepository;

    @Mock
    ServiceRepository serviceRepository;

    @Mock
    PayerRepository payerRepository;

    PayerSrvImpl srvImpl;

    @BeforeEach
    void setUp() {
        Mockito.reset(deviceRepository);
        Mockito.reset(serviceRepository);
        Mockito.reset(payerRepository);

        Mockito.lenient().when(payerRepository.save(any())).thenAnswer(x -> x.getArgument(0));

        srvImpl = new PayerSrvImpl(deviceRepository, serviceRepository, payerRepository);
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithValidArgs_IsSavedToRepo() {
        // Arrange
        var name = StringMother.random();
        var id = UUID.randomUUID();
        
        Mockito
            .when(payerRepository.exists(any(Specification.class)))
            .thenReturn(false);

        // Act
        srvImpl.create(id, name);

        // Assert
        Mockito.verify(payerRepository).save(argThat((Payer c) ->
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
            .when(payerRepository.exists(any(Specification.class)))
            .thenReturn(false);

        // Act
        var payer = srvImpl.create(id, name);

        // Assert
        assertEquals(id, payer.getId());
        assertEquals(name, payer.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithExistingId_ThrowsException() {
        // Arrange
        var name = StringMother.random();
        var id = UUID.randomUUID();

        Mockito
            .when(payerRepository.exists(any(Specification.class)))
            .thenReturn(true);

        // Act
        Executable handleAct = () -> srvImpl.create(id, name);

        // Assert
        assertThrows(PayerAlreadyExistsException.class, handleAct);
    }

    @Test
    @SuppressWarnings("unchecked")
    void delete_WithExistingPayer_IsDeletedFromRepo() {
        // Arrange
        var payer = PayerMother.random();

        Mockito
            .when(payerRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(payer));

        // Act
        srvImpl.delete(payer.getId());

        // Assert
        Mockito.verify(payerRepository).delete(payer);
    }

    @Test
    @SuppressWarnings("unchecked")
    void delete_WithExistingPayer_ReturnsDetails() {
        // Arrange
        var payer = PayerMother.random();

        Mockito
            .when(payerRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(payer));

        // Act
        var result = srvImpl.delete(payer.getId());

        // Assert
        assertEquals(payer.getId(), result.getId());
        assertEquals(payer.getName(), result.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void delete_WithNonExistingPayer_ThrowsException() {
        // Arrange
        var payerId = UUID.randomUUID();

        Mockito
            .when(payerRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.empty());

        // Act
        Executable deleteAct = () -> srvImpl.delete(payerId);

        // Assert
        assertThrows(PayerNotFoundException.class, deleteAct);
    }

    @Test
    @SuppressWarnings("unchecked")
    void update_WithExistingPayer_IsSavedToRepo() {
        // Arrange
        var name = StringMother.random();
        var payer = PayerMother.random();

        Mockito
            .when(payerRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(payer));

        // Act
        srvImpl.update(payer.getId(), name);

        // Assert
        Mockito.verify(payerRepository).save(argThat((Payer c) ->
            c.getId().equals(payer.getId()) &&
            c.getName().equals(name)));
    }

    @Test
    @SuppressWarnings("unchecked")
    void update_WithExistingPayer_ReturnsDetails() {
        // Arrange
        var name = StringMother.random();
        var payer = PayerMother.random();

        Mockito
            .when(payerRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(payer));

        // Act
        var result = srvImpl.update(payer.getId(), name);

        // Assert
        assertEquals(payer.getId(), result.getId());
        assertEquals(name, result.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void update_WithNonExistingPayer_ThrowsException() {
        // Arrange
        var name = StringMother.random();
        var id = UUID.randomUUID();

        Mockito
            .when(payerRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.empty());

        // Act
        Executable updateAct = () -> srvImpl.update(id, name);

        // Assert
        assertThrows(PayerNotFoundException.class, updateAct);
    }
}
