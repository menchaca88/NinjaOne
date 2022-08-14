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
import rmm.ninjaone.payments.data.ServiceMother;
import rmm.ninjaone.payments.domain.contracts.payers.PayerRepository;
import rmm.ninjaone.payments.domain.contracts.services.ServiceRepository;
import rmm.ninjaone.payments.domain.exceptions.ServiceAlreadyExistsException;
import rmm.ninjaone.payments.domain.exceptions.ServiceNotFoundException;
import rmm.ninjaone.payments.domain.models.ServiceCharge;
import rmm.ninjaone.payments.domain.services.ServiceSrvImpl;

@ExtendWith(MockitoExtension.class)
public class ServiceSrvImplTests {
    @Mock
    ServiceRepository serviceRepository;

    @Mock
    PayerRepository payerRepository;

    ServiceSrvImpl srvImpl;

    @BeforeEach
    void setUp() {
        Mockito.reset(serviceRepository);
        Mockito.reset(payerRepository);

        Mockito.lenient().when(serviceRepository.save(any())).thenAnswer(x -> x.getArgument(0));

        srvImpl = new ServiceSrvImpl(payerRepository, serviceRepository);
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithValidArgs_IsSavedToRepo() {
        // Arrange
        var id = UUID.randomUUID();
        var typeId = UUID.randomUUID();
        var typeName = StringMother.random();
        var payer = PayerMother.random();
        
        Mockito
            .when(payerRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(payer));

        // Act
        srvImpl.create(id, payer.getId(), typeId, typeName);

        // Assert
        Mockito.verify(serviceRepository).save(argThat((ServiceCharge s) ->
            s.getId().equals(id) &&
            s.getTypeId().equals(typeId) &&
            s.getTypeName().equals(typeName) &&
            s.getPayerId().equals(payer.getId())));
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithValidArgs_ReturnsDetails() {
        // Arrange
        var id = UUID.randomUUID();
        var typeId = UUID.randomUUID();
        var typeName = StringMother.random();
        var payer = PayerMother.random();
        
        Mockito
            .when(payerRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(payer));

        // Act
        var result = srvImpl.create(id, payer.getId(), typeId, typeName);

        // Assert
        assertEquals(id, result.getId());
        assertEquals(typeId, result.getTypeId());
        assertEquals(typeName, result.getTypeName());
        assertEquals(payer.getId(), result.getPayerId());
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithExistingTypeAndClient_ThrowsException() {
        // Arrange
        var id = UUID.randomUUID();
        var typeId = UUID.randomUUID();
        var typeName = StringMother.random();
        var payer = PayerMother.random();

        Mockito
            .when(serviceRepository.exists(any(Specification.class)))
            .thenReturn(true);

        Mockito
            .when(payerRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(payer));

        // Act
        Executable handleAct = () -> srvImpl.create(id, payer.getId(), typeId, typeName);

        // Assert
        assertThrows(ServiceAlreadyExistsException.class, handleAct);
    }

    @Test
    @SuppressWarnings("unchecked")
    void delete_WithExistingService_IsDeletedFromRepo() {
        // Arrange
        var service = ServiceMother.random();

        Mockito
            .when(serviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(service));

        // Act
        srvImpl.delete(service.getId());

        // Assert
        Mockito.verify(serviceRepository).delete(service);
    }

    @Test
    @SuppressWarnings("unchecked")
    void delete_WithExistingService_ReturnsDetails() {
        // Arrange
        var service = ServiceMother.random();

        Mockito
            .when(serviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(service));

        // Act
        var result = srvImpl.delete(service.getId());

        // Assert
        assertEquals(service.getId(), result.getId());
        assertEquals(service.getTypeId(), result.getTypeId());
        assertEquals(service.getTypeName(), result.getTypeName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void delete_WithNonExistingService_ThrowsException() {
        // Arrange
        var serviceId = UUID.randomUUID();

        Mockito
            .when(serviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.empty());

        // Act
        Executable deleteAct = () -> srvImpl.delete(serviceId);

        // Assert
        assertThrows(ServiceNotFoundException.class, deleteAct);
    }
}