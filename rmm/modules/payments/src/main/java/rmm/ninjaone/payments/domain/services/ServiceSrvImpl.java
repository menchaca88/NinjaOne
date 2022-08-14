package rmm.ninjaone.payments.domain.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.payments.domain.contracts.services.ServiceRepository;
import rmm.ninjaone.payments.domain.contracts.services.ServiceSrv;
import rmm.ninjaone.payments.domain.contracts.payers.PayerRepository;
import rmm.ninjaone.payments.domain.exceptions.ServiceAlreadyExistsException;
import rmm.ninjaone.payments.domain.exceptions.ServiceNotFoundException;
import rmm.ninjaone.payments.domain.exceptions.PayerNotFoundException;
import rmm.ninjaone.payments.domain.models.ServiceCharge;
import rmm.ninjaone.payments.domain.specifications.ServiceSpecifications;
import rmm.ninjaone.payments.domain.specifications.PayerSpecifications;

@Service("ServiceChargeSrvImpl")
@RequiredArgsConstructor
public class ServiceSrvImpl implements ServiceSrv {
    private final PayerRepository payerRepository;
    private final ServiceRepository serviceRepository;
    
    @Override
    public ServiceCharge create(UUID id, UUID payerId, UUID typeId, String typeName) {
        var payer = payerRepository
            .findOne(PayerSpecifications.findById(payerId))
            .orElseThrow(() -> new PayerNotFoundException(payerId));

        if (serviceRepository.exists(ServiceSpecifications.findById(id)))
            throw new ServiceAlreadyExistsException(id);

        var charge = ServiceCharge.create(id, payerId, typeId, typeName);

        payer.addCharge(charge);

        charge = serviceRepository.save(charge);
        payerRepository.save(payer);

        return charge;
    }

    @Override
    public ServiceCharge delete(UUID id) {
        var charge = serviceRepository
            .findOne(ServiceSpecifications.findById(id))
            .orElseThrow(() -> new ServiceNotFoundException(id));

        serviceRepository.delete(charge);

        return charge;
    }

    @Override
    public int deleteType(UUID typeId) {
        var charges = serviceRepository.findAll(ServiceSpecifications.findByTypeId(typeId));

        for (var charge : charges)
            serviceRepository.delete(charge);

        return charges.size();
    }

    @Override
    public int renameType(UUID typeId, String name) {
        var charges = serviceRepository.findAll(ServiceSpecifications.findByTypeId(typeId));

        for (var charge : charges) {
            charge.setTypeName(name);
            serviceRepository.save(charge);
        }

        return charges.size();
    }
}
