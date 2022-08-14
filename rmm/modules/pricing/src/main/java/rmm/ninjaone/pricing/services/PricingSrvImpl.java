package rmm.ninjaone.pricing.services;

import java.util.ArrayList;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.ports.catalog.CatalogPort;
import rmm.ninjaone.buildingblocks.ports.pricing.Package;
import rmm.ninjaone.buildingblocks.ports.pricing.PricedItem;
import rmm.ninjaone.buildingblocks.ports.pricing.PricedPackage;
import rmm.ninjaone.pricing.contracts.PricingSrv;
import rmm.ninjaone.pricing.strategies.PricingStrategy;
import rmm.ninjaone.pricing.strategies.PricingStrategy.DetailedItem;

@Service
@RequiredArgsConstructor
public class PricingSrvImpl implements PricingSrv {
    private final ObjectProvider<PricingStrategy> strategies;
    private final CatalogPort port;

    @Override
    public PricedPackage calculatePrices(Package pckg) {
        var priceds = new ArrayList<PricedItem>();

        for (var item : pckg.getItems()) {
            var type = item.hasCount()
                ? port.findDevice(item.getTypeId())
                : port.findService(item.getTypeId());
            
            if (type != null) {
                var details = new DetailedItem(item, type.getSubscription());
                var price = calculatePrice(details, pckg);
                if (price != null) {
                    var priced = new PricedItem(item, price);
                    priceds.add(priced);
                }
            }
        }

        return new PricedPackage(pckg, priceds);
    }

    private Double calculatePrice(DetailedItem item, Package pckg) {
        for (var strategy : strategies)
            if (strategy.supports(item)) 
                    return strategy.calculate(pckg, item);

        return null;
    }
}
