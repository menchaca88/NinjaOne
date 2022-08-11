package rmm.ninjaone.identity.unit.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import rmm.ninjaone.buildingblocks.application.contracts.PasswordEncrypter;
import rmm.ninjaone.identity.application.commands.UpdateUser.UpdateUserHandler;
import rmm.ninjaone.identity.data.RmmUserMother;
import rmm.ninjaone.identity.data.UpdateUserMother;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;
import rmm.ninjaone.identity.domain.exceptions.UserNotFoundException;
import rmm.ninjaone.identity.domain.models.RmmUser;

@ExtendWith(MockitoExtension.class)
public class UpdateUserTests {
    @Mock
    RmmUserRepository repository;

    @Mock
    PasswordEncrypter encrypter;

    UpdateUserHandler handler;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        Mockito.reset(encrypter);

        Mockito.lenient().when(repository.save(any())).thenAnswer(x -> x.getArgument(0));
        Mockito.lenient().when(encrypter.encode(any())).thenAnswer(x -> x.getArgument(0));

        handler = new UpdateUserHandler(repository, encrypter);
    }

    @Test
    @SuppressWarnings("unchecked")
    void handle_WithExistingUser_IsSavedToRepo() {
        // Arrange
        var command = UpdateUserMother.random();
        var user = RmmUserMother.withId(command.getId());
        
        Specification<RmmUser> anySpec = any(Specification.class);
        Mockito
            .when(repository.findOne(anySpec))
            .thenReturn(Optional.of(user));

        // Act
        handler.handle(command);

        // Assert
        Mockito.verify(repository).save(argThat((RmmUser u) ->
            u.getId().equals(command.getId()) &&
            u.getName().equals(command.getName()) &&
            u.getEmail().getAddress().equals(command.getEmail()) &&
            u.getPassword().equals(command.getPassword())
        ));
    }

    @Test
    @SuppressWarnings("unchecked")
    void handle_WithExistingUser_ReturnsUserDetails() {
        // Arrange
        var command = UpdateUserMother.random();
        var user = RmmUserMother.withId(command.getId());
        
        Specification<RmmUser> anySpec = any(Specification.class);
        Mockito
            .when(repository.findOne(anySpec))
            .thenReturn(Optional.of(user));

        // Act
        var result = handler.handle(command);

        // Assert
        assertEquals(command.getId(), result.getId());
        assertEquals(command.getName(), result.getName());
        assertEquals(command.getEmail(), result.getEmail());
    }

    @Test
    @SuppressWarnings("unchecked")
    void handle_WithNonExistingUser_ThrowsException() {
        // Arrange
        var command = UpdateUserMother.random();
        
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
