package rmm.ninjaone.api.unit.services;

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
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import an.awesome.pipelinr.Pipeline;
import rmm.ninjaone.api.data.UserDetailsMother;
import rmm.ninjaone.api.services.RmmUserDetailsService;
import rmm.ninjaone.buildingblocks.data.EmailMother;
import rmm.ninjaone.identity.application.queries.UserDetails.UserDetailsQuery;
import rmm.ninjaone.identity.domain.exceptions.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
public class RmmUserDetailsServiceTests {
    @Mock
    Pipeline pipeline;

    RmmUserDetailsService userDetails;

    @BeforeEach
    void setUp() {
        Mockito.reset(pipeline);
        userDetails = new RmmUserDetailsService(pipeline);
    }

    @Test
    void loadUserByUsername_WithExistingEmail_ReturnsUserDetails() {
        // Arrange
        var query = UserDetailsMother.queryEmail();
        var result = UserDetailsMother.withEmail(query.getEmail());

        Mockito
            .when(pipeline.send(argThat((UserDetailsQuery c) -> c.getEmail().equals(query.getEmail()))))
            .thenReturn(result);

        // Act
        var rmmUser = userDetails.loadUserByUsername(query.getEmail());

        // Assert
        assertEquals(result.getEmail(), rmmUser.getUsername());
        assertEquals(result.getPassword(), rmmUser.getPassword());
    }

    @Test
    void loadUserByUsername_WithNonExistingEmail_ThrowsUserNotFoundException() {
        // Arrange
        var email = EmailMother.random();
        
        Mockito.when(pipeline.send(any(UserDetailsQuery.class))).thenThrow(new UserNotFoundException(email));

        // Act
        Executable getUserAct = () -> userDetails.loadUserByUsername(email);

        // Assert
        assertThrows(UsernameNotFoundException.class, getUserAct);
    }
}
