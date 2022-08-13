package rmm.ninjaone.buildingblocks.application.support;

import java.util.UUID;

import lombok.Data;

@Data
public class UserContext {
    private UUID userId;
    private String[] authorities;
}
