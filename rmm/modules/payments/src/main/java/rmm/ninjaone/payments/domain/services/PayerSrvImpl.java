package rmm.ninjaone.payments.domain.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.payments.domain.contracts.devices.DeviceRepository;
import rmm.ninjaone.payments.domain.contracts.payers.PayerRepository;
import rmm.ninjaone.payments.domain.contracts.payers.PayerSrv;
import rmm.ninjaone.payments.domain.contracts.services.ServiceRepository;
import rmm.ninjaone.payments.domain.exceptions.PayerAlreadyExistsException;
import rmm.ninjaone.payments.domain.exceptions.PayerNotFoundException;
import rmm.ninjaone.payments.domain.models.DeviceCharge;
import rmm.ninjaone.payments.domain.models.Payer;
import rmm.ninjaone.payments.domain.models.ServiceCharge;
import rmm.ninjaone.payments.domain.specifications.PayerSpecifications;

@Service
@RequiredArgsConstructor
public class PayerSrvImpl implements PayerSrv {
    private final DeviceRepository deviceRepository;
    private final ServiceRepository serviceRepository;
    private final PayerRepository payerRepository;

    @Override
    public Payer create(UUID id, String name) {
        if (payerRepository.exists(PayerSpecifications.findById(id)))
            throw new PayerAlreadyExistsException(id);

        var payer = Payer.create(id, name);

        payer = payerRepository.save(payer);

        return payer;
    }

    @Override
    public Payer update(UUID id, String name) {
        var payer = payerRepository
            .findOne(PayerSpecifications.findById(id))
            .orElseThrow(() -> new PayerNotFoundException(id));

        payer.rename(name);

        payer = payerRepository.save(payer);

        return payer;
    }

    @Override
    public Payer delete(UUID id) {
        var payer = payerRepository
            .findOne(PayerSpecifications.findById(id))
            .orElseThrow(() -> new PayerNotFoundException(id));

        var devices = payer
            .getCharges()
            .stream()
            .filter(c -> c instanceof DeviceCharge)
            .map(c -> (DeviceCharge)c)
            .toList();

        var services = payer
            .getCharges()
            .stream()
            .filter(c -> c instanceof ServiceCharge)
            .map(c -> (ServiceCharge)c)
            .toList();

        for (var device : devices)
            deviceRepository.delete(device);

        for (var service : services)
            serviceRepository.delete(service);

            payerRepository.delete(payer);

        return payer;
    }
}
