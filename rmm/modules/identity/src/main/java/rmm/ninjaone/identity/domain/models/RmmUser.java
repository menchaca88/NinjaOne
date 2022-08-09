package rmm.ninjaone.identity.domain.models;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import rmm.ninjaone.buildingblocks.domain.bases.AggregateRoot;
import rmm.ninjaone.buildingblocks.domain.events.users.UserAddedEvent;
import rmm.ninjaone.buildingblocks.domain.events.users.UserDeletedEvent;
import rmm.ninjaone.buildingblocks.domain.events.users.UserRenamedEvent;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RmmUser extends AggregateRoot {
    private String name;
    private String email;
    private String password;

    private RmmUser(String name, String email) {
        super();

        this.name = name;
        this.email = email;
        changePassword("");

        AddEvent(new UserAddedEvent(getId(), name));
    }

    public String getName() {
        return name;
    }

    public void rename(@NonNull String name) {
        AddEvent(new UserRenamedEvent(getId(), this.name, name));
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(@NonNull String password) {
        this.password = password;
    }

    public static RmmUser create(@NonNull String name, @NonNull String email) {
        return new RmmUser(name, email);
    }

    @Override
    public void setDeleted() {
        AddEvent(new UserDeletedEvent(getId()));
    }
}
