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

import rmm.ninjaone.identity.application.queries.FindUser.FindUserHandler;
import rmm.ninjaone.identity.data.FindUserMother;
import rmm.ninjaone.identity.data.RmmUserMother;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;
import rmm.ninjaone.identity.domain.models.RmmUser;

@ExtendWith(MockitoExtension.class)
public class FindUserTests {
    @Mock
    RmmUserRepository repository;

    FindUserHandler handler;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        handler = new FindUserHandler(repository);
    }

    @Test
    @SuppressWarnings("unchecked")
    void handle_WithExistingUser_ReturnsUserDetails() {
        // Arrange
        var query = FindUserMother.random();
        var user = RmmUserMother.withEmail(query.getEmail());
        
        
        Specification<RmmUser> anySpec = any(Specification.class);
        Mockito
            .when(repository.findOne(anySpec))
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
        
        Specification<RmmUser> anySpec = any(Specification.class);
        Mockito
            .when(repository.findOne(anySpec))
            .thenReturn(Optional.empty());

        // Act
        var result = handler.handle(query);

        // Assert
        assertNull(result);
    }
}