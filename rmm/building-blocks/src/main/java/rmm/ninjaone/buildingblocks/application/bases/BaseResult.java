package rmm.ninjaone.buildingblocks.application.bases;

import java.util.Date;

import lombok.Getter;

@Getter
public class BaseResult {
    public BaseResult() {
        performedAt = new Date();
    }

    private final Date performedAt;
}
