package rmm.ninjaone.identity.unit.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import rmm.ninjaone.buildingblocks.application.contracts.PasswordEncrypter;
import rmm.ninjaone.identity.application.commands.CreateUser.CreateUserHandler;
import rmm.ninjaone.identity.data.CreateUserMother;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;
import rmm.ninjaone.identity.domain.exceptions.UserAlreadyExistsException;
import rmm.ninjaone.identity.domain.models.RmmUser;

@ExtendWith(MockitoExtension.class)
public class CreateUserTests {
    @Mock
    RmmUserRepository repository;

    @Mock
    PasswordEncrypter encrypter;

    CreateUserHandler handler;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        Mockito.reset(encrypter);

        Mockito.lenient().when(repository.save(any())).thenAnswer(x -> x.getArgument(0));
        Mockito.lenient().when(encrypter.encode(any())).thenAnswer(x -> x.getArgument(0));

        handler = new CreateUserHandler(repository, encrypter);
    }

    @Test
    @SuppressWarnings("unchecked")
    void handle_WithNewUser_IsSavedToRepo() {
        // Arrange
        var command = CreateUserMother.random();
        
        Mockito
            .when(repository.exists(any(Specification.class)))
            .thenReturn(false);

        // Act
        handler.handle(command);

        // Assert
        Mockito.verify(repository).save(argThat((RmmUser u) ->
            u.getName().equals(command.getName()) &&
            u.getEmail().getAddress().equals(command.getEmail()) &&
            u.getPassword().equals(command.getPassword()) &&
            u.getRole().name().equals(command.getRole())
        ));
    }

    @Test
    @SuppressWarnings("unchecked")
    void handle_WithNewUser_ReturnsUserDetails() {
        // Arrange
        var command = CreateUserMother.random();

        Mockito
            .when(repository.exists(any(Specification.class)))
            .thenReturn(false);

        // Act
        var result = handler.handle(command);

        // Assert
        assertEquals(command.getName(), result.getName());
        assertEquals(command.getEmail(), result.getEmail());
        assertEquals(command.getRole(), result.getRole());
    }

    @Test
    @SuppressWarnings("unchecked")
    void handle_WithExistingUser_ThrowsException() {
        // Arrange
        var command = CreateUserMother.random();

        Mockito
            .when(repository.exists(any(Specification.class)))
            .thenReturn(true);

        // Act
        Executable handleAct = () -> handler.handle(command);

        // Assert
        assertThrows(UserAlreadyExistsException.class, handleAct);
    }
}
