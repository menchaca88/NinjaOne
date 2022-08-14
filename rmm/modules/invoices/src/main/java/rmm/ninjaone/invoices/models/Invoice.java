package rmm.ninjaone.invoices.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import rmm.ninjaone.buildingblocks.domain.bases.AggregateRoot;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Invoice extends AggregateRoot {
    @ElementCollection private List<LineItem> items;
    private Date date;
    private UUID customerId;
    private String customerName;

    private Invoice(UUID id, UUID customerId, String customerName, Date date) {
        super(id);
        this.date = date;
        this.customerId = customerId;
        this.customerName = customerName;
        items = new ArrayList<>();
    }

    public Date getDate() {
        return date;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<LineItem> getItems() {
        return Collections.unmodifiableList(items);
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

    public static Invoice create(@NonNull UUID id, @NonNull UUID customerId, @NonNull String customerName, @NonNull Date date) {
        return new Invoice(id, customerId, customerName, date);
    }
}
