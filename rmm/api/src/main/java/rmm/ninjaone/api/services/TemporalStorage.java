package rmm.ninjaone.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class TemporalStorage {
    public TemporalStorage() {
        users = new ArrayList<>();
    }
    
    private List<TemporalUser> users;
    
    public TemporalUser Add(String name, String email, String password) {
        var user = new TemporalUser();
        user.id = UUID.randomUUID();
        user.name = name;
        user.email = email;
        user.password = password;

        users.add(user);

        return user;
    }

    public TemporalUser findByEmail(String email) {
        return users.stream().filter(user -> user.email.equalsIgnoreCase(email)).findFirst().orElse(null);
    }
}
