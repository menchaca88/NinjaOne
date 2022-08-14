package rmm.ninjaone.pricing.strategies;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.ports.pricing.Item;
import rmm.ninjaone.buildingblocks.ports.pricing.Package;

@RequiredArgsConstructor
public abstract class BasePricingStrategy<T> implements PricingStrategy {
    private final ObjectMapper objectMapper;
    private final Class<T> clazz;

    @Override
    public boolean supports(DetailedItem item) {
        return supportedType().equals(item.getSubscription().getType());
    }

    protected String supportedType() {
        String SUFIX = "PricingStrategy";

        var name = getClass().getSimpleName();
        if (name.endsWith(SUFIX))
            name = name.substring(0, name.length() - SUFIX.length());
        
        return name;
    }

    @Override
    public double calculate(Package pckg, DetailedItem item) {
        var raw = objectMapper.convertValue(item.getSubscription().getData(), clazz);

        var devices = pckg
            .getItems()
            .stream()
            .filter(i -> i.hasCount())
            .toList();

        var services = pckg
            .getItems()
            .stream()
            .filter(i -> !i.hasCount())
            .toList();
        
        return calculate(devices, services, item.getItem(), raw);
    }
    
    protected abstract double calculate(List<Item> devices, List<Item> services, Item current, T subscription);
}
