package rmm.ninjaone.payments.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import rmm.ninjaone.buildingblocks.ports.pricing.Package;
import rmm.ninjaone.buildingblocks.ports.pricing.PricedItem;
import rmm.ninjaone.buildingblocks.ports.pricing.PricedPackage;
import rmm.ninjaone.buildingblocks.ports.pricing.PricingPort;
import rmm.ninjaone.payments.data.PayerMother;
import rmm.ninjaone.payments.domain.contracts.payers.PayerRepository;
import rmm.ninjaone.payments.domain.services.BillingSrvImpl;

@ExtendWith(MockitoExtension.class)
public class BillingSrvImplTests {
    @Mock
    PricingPort port;

    @Mock
    PayerRepository repository;

    BillingSrvImpl srvImpl;

    @BeforeEach
    void setUp() {
        Mockito.reset(repository);
        mockPricingPort();

        srvImpl = new BillingSrvImpl(port, repository);
    }

    private void mockPricingPort() {
        Mockito.reset(port);

        Mockito
            .when(port.calulatePrice(any()))
            .thenAnswer(x -> {
                var pckg = (Package)x.getArgument(0);
                var priceds = pckg
                    .getItems()
                    .stream()
                    .map(i -> new PricedItem(i, 1))
                    .toList();

                return new PricedPackage(pckg, priceds);
            });
    }

    @Test
    @SuppressWarnings("unchecked")
    void generate_WithValidData_ReturnsSameData() {
        // Arrange
        var payer = PayerMother.withCharges();

        Mockito
            .when(repository.findOne(any(Specification.class)))
            .thenReturn(Optional.of(payer));

        // Act
        var bill = srvImpl.generate(payer.getId());

        // Assert
        assertEquals(payer.getId(), bill.getPayerId());
        assertEquals(payer.getName(), bill.getPayerName());
        assertEquals(payer.getCharges().size(), bill.getTotal());
    }
}