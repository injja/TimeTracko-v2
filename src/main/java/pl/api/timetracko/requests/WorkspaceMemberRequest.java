package pl.api.timetracko.requests;

import lombok.Data;
import pl.api.timetracko.config.securityModels.Role;

@Data
public class WorkspaceMemberRequest {
    Long user_id;
    Long workspace_id;
    Long role_id;
    public WorkspaceMemberRequest() {

    }
}
