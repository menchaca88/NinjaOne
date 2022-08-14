package rmm.ninjaone.api.support.setup;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan({ 
    "rmm.ninjaone.buildingblocks",
    "rmm.ninjaone.identity",
    "rmm.ninjaone.catalog",
    "rmm.ninjaone.inventory",
    "rmm.ninjaone.payments",
    "rmm.ninjaone.pricing",
    "rmm.ninjaone.invoices"
})
@EnableJpaRepositories({ 
    "rmm.ninjaone.buildingblocks",
    "rmm.ninjaone.identity",
    "rmm.ninjaone.catalog",
    "rmm.ninjaone.inventory",
    "rmm.ninjaone.payments",
    "rmm.ninjaone.pricing",
    "rmm.ninjaone.invoices",
})
@ComponentScan("rmm.ninjaone.identity")
@ComponentScan("rmm.ninjaone.catalog")
@ComponentScan("rmm.ninjaone.inventory")
@ComponentScan("rmm.ninjaone.invoices")
@ComponentScan("rmm.ninjaone.payments")
@ComponentScan("rmm.ninjaone.pricing")
@ComponentScan("rmm.ninjaone.buildingblocks")
public class ModulesScan {
}
