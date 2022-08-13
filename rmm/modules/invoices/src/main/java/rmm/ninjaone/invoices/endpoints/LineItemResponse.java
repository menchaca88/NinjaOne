package rmm.ninjaone.invoices.endpoints;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineItemResponse {
    private String name;
    private Integer count;
    private double cost;
}
