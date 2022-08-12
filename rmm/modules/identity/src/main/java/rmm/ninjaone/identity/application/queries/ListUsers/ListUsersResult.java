package rmm.ninjaone.identity.application.queries.ListUsers;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import rmm.ninjaone.buildingblocks.application.bases.BaseResult;

@Getter
@Setter
public class ListUsersResult extends BaseResult {
    private List<ListUsersItem> users;
}
