package rmm.ninjaone.invoices.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import rmm.ninjaone.buildingblocks.domain.bases.AggregateRoot;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Invoice extends AggregateRoot {
    @ElementCollection private List<LineItem> items;
    private Date date;
    private UUID customerId;

    private Invoice(UUID customerId, Date date) {
        super();

        this.date = date;
        this.customerId = customerId;
        items = new ArrayList<>();
    }

    public double getTotal() {
        return items
            .stream()
            .map(li -> li.getCost())
            .reduce(0.0, Double::sum);
    }

    public LineItem addItem(@NonNull String name, double cost) {
        var item = new LineItem(name, null, cost);
        items.add(item);

        return item;
    }

    public LineItem addItem(@NonNull String name, int count, double cost) {
        if (count <= 0)
            throw new IllegalArgumentException();

        var item = new LineItem(name, count, cost);
        items.add(item);

        return item;
    }

    public static Invoice create(@NonNull UUID customerId, @NonNull Date date) {
        return new Invoice(customerId, date);
    }
}
