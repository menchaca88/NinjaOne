package rmm.ninjaone.identity.unit.commands;

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

import rmm.ninjaone.identity.application.commands.DeleteUser.DeleteUserHandler;
import rmm.ninjaone.identity.data.DeleteUserMother;
import rmm.ninjaone.identity.data.RmmUserMother;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;
import rmm.ninjaone.identity.domain.exceptions.UserNotFoundException;
import rmm.ninjaone.identity.domain.models.RmmUser;

@ExtendWith(MockitoExtension.class)
public class DeleteUserTests {
    @Mock
    RmmUserRepository repository;

    DeleteUserHandler handler;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        handler = new DeleteUserHandler(repository);
    }

    @Test
    @SuppressWarnings("unchecked")
    void handle_WithExistingUser_IsDeletedFromRepo() {
        // Arrange
        var command = DeleteUserMother.random();
        var user = RmmUserMother.withId(command.getId());
        
        Specification<RmmUser> anySpec = any(Specification.class);
        Mockito
            .when(repository.findOne(anySpec))
            .thenReturn(Optional.of(user));

        // Act
        handler.handle(command);

        // Assert
        Mockito.verify(repository).delete(user);
    }

    @Test
    @SuppressWarnings("unchecked")
    void handle_WithExistingUser_ReturnsUserDetails() {
        // Arrange
        var command = DeleteUserMother.random();
        var user = RmmUserMother.withId(command.getId());
        
        Specification<RmmUser> anySpec = any(Specification.class);
        Mockito
            .when(repository.findOne(anySpec))
            .thenReturn(Optional.of(user));

        // Act
        var result = handler.handle(command);

        // Assert
        assertEquals(command.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getEmail().getAddress(), result.getEmail());
        assertEquals(user.getRole().name(), result.getRole());
    }

    @Test
    @SuppressWarnings("unchecked")
    void handle_WithNonExistingUser_ThrowsException() {
        // Arrange
        var command = DeleteUserMother.random();
        
        Specification<RmmUser> anySpec = any(Specification.class);
        Mockito
            .when(repository.findOne(anySpec))
            .thenReturn(Optional.empty());

        // Act
        Executable handleAct = () -> handler.handle(command);

        // Assert
        assertThrows(UserNotFoundException.class, handleAct);
    }
}
