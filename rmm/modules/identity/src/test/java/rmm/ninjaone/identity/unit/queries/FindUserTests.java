package rmm.ninjaone.identity.unit.queries;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import rmm.ninjaone.buildingblocks.application.support.UserContext;
import rmm.ninjaone.identity.application.queries.FindUser.FindUserHandler;
import rmm.ninjaone.identity.data.FindUserMother;
import rmm.ninjaone.identity.data.RmmUserMother;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;

@ExtendWith(MockitoExtension.class)
public class FindUserTests {
    @Mock
    RmmUserRepository repository;

    @Mock
    UserContext userContext;

    FindUserHandler handler;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        handler = new FindUserHandler(userContext, repository);
    }

    @Test
    @SuppressWarnings("unchecked")
    void handle_WithExistingUser_ReturnsUserDetails() {
        // Arrange
        var query = FindUserMother.random();
        var user = RmmUserMother.withEmail(query.getEmail());
        
        Mockito
            .when(repository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(user));

        // Act
        var result = handler.handle(query);

        // Assert
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getEmail().getAddress(), result.getEmail());
        assertEquals(user.getRole().name(), result.getRole());
    }

    @Test
    @SuppressWarnings("unchecked")
    void handle_WithNonExistingUser_ReturnsNull() {
        // Arrange
        var query = FindUserMother.random();

        Mockito
            .when(repository.findOne(any(Specification.class)))
            .thenReturn(Optional.empty());

        // Act
        var result = handler.handle(query);

        // Assert
        assertNull(result);
    }
}
