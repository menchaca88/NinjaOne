package rmm.ninjaone.catalog.domain.models;

public interface Subscription {
    default String getName() {
        String SUFIX = "Subscription";

        var name = getClass().getSimpleName();
        if (name.endsWith(SUFIX))
            name = name.substring(0, name.length() - SUFIX.length());
        
        return name;
    }
}
