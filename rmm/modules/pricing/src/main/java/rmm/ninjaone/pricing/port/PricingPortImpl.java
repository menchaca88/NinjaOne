package rmm.ninjaone.pricing.port;

import org.springframework.stereotype.Service;

import rmm.ninjaone.buildingblocks.ports.pricing.Package;
import rmm.ninjaone.buildingblocks.ports.pricing.PricedItem;
import rmm.ninjaone.buildingblocks.ports.pricing.PricedPackage;
import rmm.ninjaone.buildingblocks.ports.pricing.PricingPort;

@Service
public class PricingPortImpl implements PricingPort {
    @Override
    public PricedPackage calulatePrice(Package pckg) {
        var priceds = pckg
            .getItems()
            .stream()
            .map(i -> new PricedItem(i, 1))
            .toList();

        return new PricedPackage(pckg, priceds);
    }
}
