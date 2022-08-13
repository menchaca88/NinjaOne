package rmm.ninjaone.catalog.unit;

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
import rmm.ninjaone.catalog.data.ServiceMother;
import rmm.ninjaone.catalog.data.SubscriptionMother;
import rmm.ninjaone.catalog.domain.contracts.devices.DeviceRepository;
import rmm.ninjaone.catalog.domain.contracts.services.ServiceRepository;
import rmm.ninjaone.catalog.domain.exceptions.ServiceAlreadyExistsException;
import rmm.ninjaone.catalog.domain.exceptions.ServiceNotFoundException;
import rmm.ninjaone.catalog.domain.models.services.ServiceType;
import rmm.ninjaone.catalog.domain.services.ServiceSrvImpl;

@ExtendWith(MockitoExtension.class)
public class ServiceSrvImplTests {
    @Mock
    ServiceRepository serviceRepository;

    @Mock
    DeviceRepository deviceRepository;

    ServiceSrvImpl srvImpl;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        Mockito.reset(serviceRepository);
        Mockito.reset(deviceRepository);

        Mockito.lenient().when(serviceRepository.save(any())).thenAnswer(x -> x.getArgument(0));
        Mockito.lenient().when(deviceRepository.exists(any(Specification.class))).thenReturn(true);

        srvImpl = new ServiceSrvImpl(deviceRepository, serviceRepository);
    }

    @Test
    @SuppressWarnings("unchecked")
    void get_WithExistingId_ReturnsServiceDetails() {
        // Arrange
        var service = ServiceMother.random();

        Mockito
            .when(serviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(service));

        // Act
        var result = srvImpl.get(service.getId());

        // Assert
        assertEquals(service.getId(), result.getId());
        assertEquals(service.getName(), result.getName());
        assertEquals(service.getSku(), result.getSku());
    }

    @Test
    @SuppressWarnings("unchecked")
    void get_WithExistingSku_ReturnsServiceDetails() {
        // Arrange
        var service = ServiceMother.random();

        Mockito
            .when(serviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(service));

        // Act
        var result = srvImpl.get(service.getSku());

        // Assert
        assertEquals(service.getId(), result.getId());
        assertEquals(service.getName(), result.getName());
        assertEquals(service.getSku(), result.getSku());
    }

    @Test
    void getAll_ServicesInRepo_ReturnsServicesList() {
        // Arrange
        var services = ServiceMother.count(5);
        
        Mockito
            .when(serviceRepository.findAll())
            .thenReturn(services);

        // Act
        var result = srvImpl.getAll();

        // Assert
        assertEquals(services.size(), result.size());
        for (int i = 0; i < services.size(); i++) {
            assertEquals(services.get(i).getId(), result.get(i).getId());
            assertEquals(services.get(i).getName(), result.get(i).getName());
            assertEquals(services.get(i).getSku(), result.get(i).getSku());
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithValidName_IsSavedToRepo() {
        // Arrange
        var name = StringMother.random();
        var subscription = SubscriptionMother.serviceRandom();
        
        Mockito
            .when(serviceRepository.exists(any(Specification.class)))
            .thenReturn(false);

        // Act
        srvImpl.create(name, subscription);

        // Assert
        Mockito.verify(serviceRepository).save(argThat((ServiceType d) ->
            d.getName().equals(name)));
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithValidName_ReturnsServiceDetails() {
        // Arrange
        var name = StringMother.random();
        var subscription = SubscriptionMother.serviceRandom();
        
        Mockito
            .when(serviceRepository.exists(any(Specification.class)))
            .thenReturn(false);

        // Act
        var service = srvImpl.create(name, subscription);

        // Assert
        assertEquals(name, service.getName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithExistingName_ThrowsException() {
        // Arrange
        var name = StringMother.random();
        var subscription = SubscriptionMother.serviceRandom();

        Mockito
            .when(serviceRepository.exists(any(Specification.class)))
            .thenReturn(true);

        // Act
        Executable handleAct = () -> srvImpl.create(name, subscription);

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
    void delete_WithExistingService_ReturnsServiceDetails() {
        // Arrange
        var service = ServiceMother.random();

        Mockito
            .when(serviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(service));

        // Act
        var result = srvImpl.delete(service.getId());

        // Assert
        assertEquals(service.getId(), result.getId());
        assertEquals(service.getName(), result.getName());
        assertEquals(service.getSku(), result.getSku());
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

    @Test
    @SuppressWarnings("unchecked")
    void update_WithExistingService_IsSavedToRepo() {
        // Arrange
        var name = StringMother.random();
        var service = ServiceMother.random();

        Mockito
            .when(serviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(service));

        // Act
        srvImpl.update(service.getId(), name);

        // Assert
        Mockito.verify(serviceRepository).save(argThat((ServiceType d) ->
            d.getId().equals(service.getId()) &&
            d.getName().equals(name)));
    }

    @Test
    @SuppressWarnings("unchecked")
    void update_WithExistingService_ReturnsServiceDetails() {
        // Arrange
        var name = StringMother.random();
        var service = ServiceMother.random();

        Mockito
            .when(serviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(service));

        // Act
        var result = srvImpl.update(service.getId(), name);

        // Assert
        assertEquals(service.getId(), result.getId());
        assertEquals(name, result.getName());
        assertEquals(service.getSku(), result.getSku());
    }

    @Test
    @SuppressWarnings("unchecked")
    void update_WithNonExistingService_ThrowsException() {
        // Arrange
        var name = StringMother.random();
        var serviceId = UUID.randomUUID();

        Mockito
            .when(serviceRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.empty());

        // Act
        Executable updateAct = () -> srvImpl.update(serviceId, name);

        // Assert
        assertThrows(ServiceNotFoundException.class, updateAct);
    }
}
