package rmm.ninjaone.identity.unit.queries;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import rmm.ninjaone.identity.application.queries.ListUsers.ListUsersHandler;
import rmm.ninjaone.identity.application.queries.ListUsers.ListUsersQuery;
import rmm.ninjaone.identity.data.RmmUserMother;
import rmm.ninjaone.identity.domain.contracts.RmmUserRepository;

@ExtendWith(MockitoExtension.class)
public class ListUsersTests {
    @Mock
    RmmUserRepository repository;

    ListUsersHandler handler;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        handler = new ListUsersHandler(repository);
    }

    @Test
    void handle_UsersInRepo_ReturnsUsersList() {
        // Arrange
        var query = new ListUsersQuery();
        var users = RmmUserMother.count(5);
        
        Mockito
            .when(repository.findAll())
            .thenReturn(users);

        // Act
        var result = handler.handle(query).getUsers();

        // Assert
        assertEquals(users.size(), result.size());
        for (int i = 0; i < users.size(); i++) {
            assertEquals(users.get(i).getId(), result.get(i).getId());
            assertEquals(users.get(i).getName(), result.get(i).getName());
            assertEquals(users.get(i).getEmail().getAddress(), result.get(i).getEmail());
            assertEquals(users.get(i).getRole().name(), result.get(i).getRole());
        }
    }
}