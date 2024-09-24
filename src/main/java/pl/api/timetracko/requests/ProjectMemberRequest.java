package pl.api.timetracko.requests;

import lombok.Data;
import pl.api.timetracko.Enums.Role;

import java.util.List;
@Data
public class ProjectMemberRequest {
    Long project_id;
    Long workplace_member_id;
    Role role;
}
