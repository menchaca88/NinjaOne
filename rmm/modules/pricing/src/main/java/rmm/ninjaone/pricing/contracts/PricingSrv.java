package rmm.ninjaone.pricing.contracts;

import rmm.ninjaone.buildingblocks.ports.pricing.Package;
import rmm.ninjaone.buildingblocks.ports.pricing.PricedPackage;

public interface PricingSrv {
    PricedPackage calculatePrices(Package pckg);
}
