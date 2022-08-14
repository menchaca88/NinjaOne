package rmm.ninjaone.payments.domain.services;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.buildingblocks.ports.pricing.Item;
import rmm.ninjaone.buildingblocks.ports.pricing.Package;
import rmm.ninjaone.buildingblocks.ports.pricing.PricingPort;
import rmm.ninjaone.payments.domain.contracts.billings.BillingSrv;
import rmm.ninjaone.payments.domain.contracts.payers.PayerRepository;
import rmm.ninjaone.payments.domain.exceptions.PayerNotFoundException;
import rmm.ninjaone.payments.domain.exceptions.UnknownPriceException;
import rmm.ninjaone.payments.domain.models.Bill;
import rmm.ninjaone.payments.domain.models.Charge;
import rmm.ninjaone.payments.domain.models.DeviceCharge;
import rmm.ninjaone.payments.domain.models.ServiceCharge;
import rmm.ninjaone.payments.domain.specifications.PayerSpecifications;

@Service
@RequiredArgsConstructor
public class BillingSrvImpl implements BillingSrv {
    private final PricingPort port;
    private final PayerRepository repository;

    @Override
    public Bill generate(UUID payerId) {
        var payer = repository
            .findOne(PayerSpecifications.findById(payerId))
            .orElseThrow(() -> new PayerNotFoundException(payerId));

        var items = new ArrayList<Item>();
        for (var charge : payer.getCharges()) {
            Item item = null;

            if (charge instanceof DeviceCharge) {
                var device = (DeviceCharge)charge;

                item = Item.forDevice(device.getTypeId(), device.getCount());
            }

            if (charge instanceof ServiceCharge) {
                var service = (ServiceCharge)charge;

                item = Item.forService(service.getTypeId());
            }

            if (item != null)
                items.add(item);
        }

        var pckg = new Package(items);
        var pricedPackage = port.calulatePrice(pckg);

        if (!pricedPackage.fullyPriced())
            throw new UnknownPriceException();

        var bill = new Bill(payer.getId(), payer.getName());

        var charges = payer
            .getCharges()
            .stream()
            .collect(Collectors.toMap(Charge::getTypeId, c -> c.getTypeName()));

        for (var item : pricedPackage.getPricedItems()) {
            if (item.hasCount())
                bill.addDeviceItem(charges.get(item.getTypeId()), item.getCount(), item.getPrice());
            else
                bill.addServiceItem(charges.get(item.getTypeId()), item.getPrice());
        }

        return bill;
    }
}
