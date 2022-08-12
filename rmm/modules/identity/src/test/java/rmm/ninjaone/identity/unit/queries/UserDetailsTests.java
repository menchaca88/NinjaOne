package rmm.ninjaone.identity.unit.queries;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import rmm.ninjaone.identity.application.queries.UserDetails.UserDetailsHandler;
import rmm.ninjaone.identity.data.RmmUserMother;
import rmm.ninjaone.identity.data.UserDetailsMother;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;
import rmm.ninjaone.identity.domain.exceptions.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserDetailsTests {
    @Mock
    RmmUserRepository repository;

    UserDetailsHandler handler;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        handler = new UserDetailsHandler(repository);
    }

    @Test
    @SuppressWarnings("unchecked")
    void handle_WithExistingId_ReturnsUserDetails() {
        // Arrange
        var query = UserDetailsMother.byId();
        var user = RmmUserMother.withId(query.getId());
        
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
    void handle_WithExistingEmail_ReturnsUserDetails() {
        // Arrange
        var query = UserDetailsMother.byEmail();
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
    void handle_WithNonExistingUser_ThrowsException() {
        // Arrange
        var query = UserDetailsMother.byEmail();

        Mockito
            .when(repository.findOne(any(Specification.class)))
            .thenReturn(Optional.empty());

        // Act
        Executable handleAct = () -> handler.handle(query);

        // Assert
        assertThrows(UserNotFoundException.class, handleAct);
    }
}