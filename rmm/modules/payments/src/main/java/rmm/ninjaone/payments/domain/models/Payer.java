package rmm.ninjaone.payments.domain.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import rmm.ninjaone.buildingblocks.domain.bases.AggregateRoot;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payer extends AggregateRoot {
    @OneToMany 
    @JoinColumn(name = "payerId")
    private List<Charge> charges;
    private String name;

    private Payer(UUID id, String name) {
        super(id);
        this.name = name;
        charges = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void rename(@NonNull String name) {
        this.name = name;
    }

    public void addCharge(@NonNull Charge charge) {
        charges.add(charge);
    }

    public List<Charge> getCharges() {
        return Collections.unmodifiableList(charges);
    }

    public static Payer create(@NonNull UUID id, @NonNull String name) {
        return new Payer(id, name);
    }
}
