package rmm.ninjaone.pricing.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;

import rmm.ninjaone.pricing.data.ItemMother;
import rmm.ninjaone.pricing.data.PackageMother;
import rmm.ninjaone.pricing.data.SubscriptionMother;
import rmm.ninjaone.pricing.strategies.PerUnitDevicePricingStrategy;
import rmm.ninjaone.pricing.strategies.PricingStrategy;

@ExtendWith(MockitoExtension.class)
public class PerUnitDevicePricingStrategyTests {
    PerUnitDevicePricingStrategy strategy;

    @BeforeEach
    void setUp() {
        var objectMapper = new ObjectMapper();
        strategy = new PerUnitDevicePricingStrategy(objectMapper);
    }

    @Test
    void supports_PerUnitDevice_ReturnsTrue() {
        // Arrange
        var item = ItemMother.device();
        var data = SubscriptionMother.perUnitDevice(5);
        var details = new PricingStrategy.DetailedItem(item, data);

        // Act
        var result = strategy.supports(details);

        // Assert
        assertTrue(result);
    }

    @Test
    void calulate_withPackage_ReturnsCorrectPrice() {
        // Arrange
        var cost = 5;
        var item = ItemMother.device();
        var data = SubscriptionMother.perUnitDevice(cost);
        var pckg = PackageMother.random(item, 5, 5);
        var details = new PricingStrategy.DetailedItem(item, data);

        // Act
        var result = strategy.calculate(pckg, details);

        // Assert
        assertEquals(cost * item.getCount(), result);
    }
}
