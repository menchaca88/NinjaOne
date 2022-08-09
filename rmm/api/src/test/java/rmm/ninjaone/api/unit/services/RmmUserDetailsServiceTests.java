package rmm.ninjaone.api.unit.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import rmm.ninjaone.api.data.UserMother;
import rmm.ninjaone.api.services.RmmUserDetailsService;
import rmm.ninjaone.api.support.TemporalStorage;
import rmm.ninjaone.buildingblocks.data.EmailMother;

@ExtendWith(MockitoExtension.class)
public class RmmUserDetailsServiceTests {
    @Mock
    TemporalStorage storage;

    RmmUserDetailsService userDetails;

    @BeforeEach
    void setUp() {
        Mockito.reset(storage);
        userDetails = new RmmUserDetailsService(storage);
    }

    @Test
    void loadUserByUsername_WithExistingEmail_ReturnsUserDetails() {
        // Arrange
        var user = UserMother.random();
        Mockito.when(storage.findByEmail(user.email)).thenReturn(user);

        // Act
        var rmmUser = userDetails.loadUserByUsername(user.email);

        // Assert
        assertEquals(user.email, rmmUser.getUsername());
        assertEquals(user.password, rmmUser.getPassword());
    }

    @Test
    void loadUserByUsername_WithNonExistingEmail_ThrowsUserNotFoundException() {
        // Arrange
        var email = EmailMother.random();
        Mockito.when(storage.findByEmail(anyString())).thenReturn(null);

        // Act
        Executable getUserAct = () -> userDetails.loadUserByUsername(email);

        // Assert
        assertThrows(UsernameNotFoundException.class, getUserAct);
    }
}
