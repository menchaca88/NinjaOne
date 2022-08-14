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
import rmm.ninjaone.inventory.data.DetailsMother;
import rmm.ninjaone.inventory.data.ServiceMother;
import rmm.ninjaone.inventory.domain.contracts.clients.ClientRepository;
import rmm.ninjaone.inventory.domain.contracts.services.ServiceRepository;
import rmm.ninjaone.inventory.domain.exceptions.ServiceAlreadyExistsException;
import rmm.ninjaone.inventory.domain.exceptions.ServiceNotFoundException;
import rmm.ninjaone.inventory.domain.factories.ServiceFactory;
import rmm.ninjaone.inventory.domain.models.Service;
import rmm.ninjaone.inventory.domain.services.ServiceSrvImpl;

@ExtendWith(MockitoExtension.class)
public class ServiceSrvImplTests {
    @Mock
    ServiceRepository serviceRepository;

    @Mock
    ClientRepository clientRepository;

    @Mock
    CatalogPort catalogPort;

    ServiceSrvImpl srvImpl;

    @BeforeEach
    void setUp() {
        Mockito.reset(serviceRepository);
        Mockito.reset(clientRepository);
        Mockito.reset(catalogPort);

        Mockito.lenient().when(serviceRepository.save(any())).thenAnswer(x -> x.getArgument(0));

        var factory = new ServiceFactory(catalogPort);
        srvImpl = new ServiceSrvImpl(clientRepository, serviceRepository, factory);
    }

    @Test
    @SuppressWarnings("unchecked")
    void getAll_ServicesInRepo_ReturnsServicesList() {
        // Arrange
        var clientId = UUID.randomUUID();
        var services = ServiceMother.countWithClient(clientId, 5);
        
        Mockito
            .when(serviceRepository.findAll(any(Specification.class)))
            .thenReturn(services);

        // Act
        var result = srvImpl.getAll(clientId);

        // Assert
        assertEquals(services.size(), result.size());
        for (int i = 0; i < services.size(); i++) {
            assertEquals(services.get(i).getId(), result.get(i).getId());
            assertEquals(services.get(i).getTypeId(), result.get(i).getTypeId());
            assertEquals(clientId, result.get(i).getClientId());
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithValidArgs_IsSavedToRepo() {
        // Arrange
        var client = ClientMother.random();
        var type = DetailsMother.randomType();
        
        Mockito
            .when(clientRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(client));

        Mockito
            .when(catalogPort.findService(type.getId()))
            .thenReturn(type);

        // Act
        srvImpl.create(client.getId(), type.getId());

        // Assert
        Mockito.verify(serviceRepository).save(argThat((Service s) ->
            s.getClientId().equals(client.getId()) &&
            s.getTypeId().equals(type.getId()) &&
            s.getTypeName().equals(type.getName())));
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithValidArgs_ReturnsDetails() {
        // Arrange
        var client = ClientMother.random();
        var type = DetailsMother.randomType();
        
        Mockito
            .when(clientRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(client));

        Mockito
            .when(catalogPort.findService(type.getId()))
            .thenReturn(type);

        // Act
        var result = srvImpl.create(client.getId(), type.getId());

        // Assert
        assertEquals(client.getId(), result.getClientId());
        assertEquals(type.getId(), result.getTypeId());
        assertEquals(type.getName(), result.getTypeName());
    }

    @Test
    @SuppressWarnings("unchecked")
    void create_WithExistingTypeAndClient_ThrowsException() {
        // Arrange
        var client = ClientMother.random();
        var typeId = UUID.randomUUID();

        Mockito
            .when(serviceRepository.exists(any(Specification.class)))
            .thenReturn(true);

        Mockito
            .when(clientRepository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(client));

        // Act
        Executable handleAct = () -> srvImpl.create(client.getId(), typeId);

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