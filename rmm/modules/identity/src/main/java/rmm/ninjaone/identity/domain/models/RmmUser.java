package rmm.ninjaone.identity.domain.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import rmm.ninjaone.buildingblocks.domain.bases.AggregateRoot;
import rmm.ninjaone.buildingblocks.domain.events.users.UserAddedEvent;
import rmm.ninjaone.buildingblocks.domain.events.users.UserDeletedEvent;
import rmm.ninjaone.buildingblocks.domain.events.users.UserRenamedEvent;
import rmm.ninjaone.buildingblocks.domain.valueObjects.Email;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RmmUser extends AggregateRoot {
    @Enumerated(EnumType.STRING)
    private RmmRole role;
    private String name;
    private Email email;
    private String password;

    private RmmUser(String name, RmmRole role, Email email) {
        super();

        this.name = name;
        this.email = email;
        this.role = role;
        this.password = "";

        registerEvent(new UserAddedEvent(getId(), name));
    }

    public String getName() {
        return name;
    }

    public void rename(@NonNull String name) {
        registerEvent(new UserRenamedEvent(getId(), this.name, name));
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(@NonNull Email email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(@NonNull String password) {
        this.password = password;
    }

    public RmmRole getRole() {
        return role;
    }

    public void setRole(@NonNull RmmRole role) {
        this.role = role;
    }

    public static RmmUser create(@NonNull String name, @NonNull RmmRole role, @NonNull Email email) {
        return new RmmUser(name, role, email);
    }

    @Override
    protected void deleted() {
        registerEvent(new UserDeletedEvent(getId()));
    }
}
