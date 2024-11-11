package pl.api.timetracko.requests;

import lombok.Data;
import pl.api.timetracko.config.securityModels.Role;

@Data
public class ProjectMemberRequest {
    Long project_id;
    Long user_id;
    Long role_id;
}
