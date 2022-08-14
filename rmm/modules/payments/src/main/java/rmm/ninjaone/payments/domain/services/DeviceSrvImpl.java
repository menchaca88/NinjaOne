package rmm.ninjaone.payments.domain.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.payments.domain.contracts.devices.DeviceRepository;
import rmm.ninjaone.payments.domain.contracts.devices.DeviceSrv;
import rmm.ninjaone.payments.domain.contracts.payers.PayerRepository;
import rmm.ninjaone.payments.domain.exceptions.DeviceAlreadyExistsException;
import rmm.ninjaone.payments.domain.exceptions.DeviceNotFoundException;
import rmm.ninjaone.payments.domain.exceptions.PayerNotFoundException;
import rmm.ninjaone.payments.domain.models.DeviceCharge;
import rmm.ninjaone.payments.domain.specifications.DeviceSpecifications;
import rmm.ninjaone.payments.domain.specifications.PayerSpecifications;

@Service("DeviceChargeSrvImpl")
@RequiredArgsConstructor
public class DeviceSrvImpl implements DeviceSrv {
    private final PayerRepository payerRepository;
    private final DeviceRepository deviceRepository;
    
    @Override
    public DeviceCharge create(UUID id, UUID payerId, int count, UUID typeId, String typeName) {
        var payer = payerRepository
            .findOne(PayerSpecifications.findById(payerId))
            .orElseThrow(() -> new PayerNotFoundException(payerId));

        if (deviceRepository.exists(DeviceSpecifications.findById(id)))
            throw new DeviceAlreadyExistsException(id);

        var charge = DeviceCharge.create(id, payerId, typeId, typeName, count);

        payer.addCharge(charge);

        charge = deviceRepository.save(charge);
        payerRepository.save(payer);

        return charge;
    }

    @Override
    public DeviceCharge update(UUID id, int count) {
        var charge = deviceRepository
            .findOne(DeviceSpecifications.findById(id))
            .orElseThrow(() -> new DeviceNotFoundException(id));

        charge.setCount(count);

        charge = deviceRepository.save(charge);

        return charge;
    }

    @Override
    public DeviceCharge delete(UUID id) {
        var charge = deviceRepository
            .findOne(DeviceSpecifications.findById(id))
            .orElseThrow(() -> new DeviceNotFoundException(id));

        deviceRepository.delete(charge);

        return charge;
    }

    @Override
    public int deleteType(UUID typeId) {
        var charges = deviceRepository.findAll(DeviceSpecifications.findByTypeId(typeId));

        for (var charge : charges)
            deviceRepository.delete(charge);

        return charges.size();
    }

    @Override
    public int renameType(UUID typeId, String name) {
        var charges = deviceRepository.findAll(DeviceSpecifications.findByTypeId(typeId));

        for (var charge : charges) {
            charge.setTypeName(name);
            deviceRepository.save(charge);
        }

        return charges.size();
    }
}
