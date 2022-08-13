package rmm.ninjaone.invoices.models;

import javax.persistence.Embeddable;

import lombok.Value;

@Value
@Embeddable
public class LineItem {
    private String name;
    private Integer count;
    private double cost;
}
