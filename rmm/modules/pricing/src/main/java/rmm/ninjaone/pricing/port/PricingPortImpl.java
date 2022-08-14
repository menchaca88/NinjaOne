package rmm.ninjaone.pricing.port;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.ports.pricing.Package;
import rmm.ninjaone.buildingblocks.ports.pricing.PricedPackage;
import rmm.ninjaone.buildingblocks.ports.pricing.PricingPort;
import rmm.ninjaone.pricing.contracts.PricingSrv;

@Service
@RequiredArgsConstructor
public class PricingPortImpl implements PricingPort {
    private final PricingSrv pricingSrv;

    @Override
    public PricedPackage calulatePrice(Package pckg) {
        return pricingSrv.calculatePrices(pckg);
    }
}
