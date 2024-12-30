package pl.api.timetracko.requests;

import lombok.Data;
import pl.api.timetracko.config.securityModels.Role;

@Data
public class ProjectMemberRequest {
    Long project_id;
    Long workspace_member_id;
    Long role_id;
}
