package rmm.ninjaone.catalog.infrastructure.persistence.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionEntity {
    private String type;
    private String data;

    @Override
    public String toString() {
        return String.format("%s.%s", type, data);
    }

    public static SubscriptionEntity parse(String content) {
        try {
            var parts = content.split("\\.", 2);

            var entity = new SubscriptionEntity();
            entity.setType(parts[0]);
            entity.setData(parts[1]);

            return entity;
        }
        catch (Exception ex) {
            return null;
        }
    }
}
