package rmm.ninjaone.identity.unit.validators;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import rmm.ninjaone.identity.application.queries.UserDetails.UserDetailsValidator;
import rmm.ninjaone.identity.data.UserDetailsMother;

@ExtendWith(MockitoExtension.class)
public class UserDetailsValidatorTests {
    @Mock
    Validator validator;

    UserDetailsValidator userValidator;

    @BeforeEach
    void setUp() {
        Mockito.reset(validator);
        userValidator = new UserDetailsValidator(validator);
    }

    @Test
    void validate_WithId_DoNotThrows() {
        // Arrange
        var query = UserDetailsMother.byId();

        // Act
        Executable valiateAct = () -> userValidator.validate(query);

        // Assert
        assertDoesNotThrow(valiateAct);
    }

    @Test
    void validate_WithEmail_DoNotThrows() {
       // Arrange
       var query = UserDetailsMother.byEmail();

       // Act
       Executable valiateAct = () -> userValidator.validate(query);

       // Assert
       assertDoesNotThrow(valiateAct);
    }

    @Test
    void validate_InvalidQuery_ThrowsException() {
       // Arrange
       var query = UserDetailsMother.invalid();

       // Act
       Executable valiateAct = () -> userValidator.validate(query);

       // Assert
       assertThrows(AssertionError.class, valiateAct);
    }
}