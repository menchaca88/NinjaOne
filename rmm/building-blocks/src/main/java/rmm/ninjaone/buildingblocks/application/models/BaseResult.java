package rmm.ninjaone.buildingblocks.application.models;

import java.util.Date;

import lombok.Getter;

@Getter
public class BaseResult {
    public BaseResult() {
        performedAt = new Date();
    }

    private final Date performedAt;
}
