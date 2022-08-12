package rmm.ninjaone.catalog.domain.models;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public interface Subscription {
    default String getName() {
        String SUFIX = "Subscription";

        var name = getClass().getSimpleName();
        if (name.endsWith(SUFIX))
            name = name.substring(0, name.length() - SUFIX.length());
        
        return name;
    }

    @Getter
    @Setter
    public static class RawData {
        private String type;
        private Map<String, Object> data;
    }
}
