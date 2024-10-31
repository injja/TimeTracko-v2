package pl.api.timetracko.requests;

import lombok.Data;

@Data
public class ProjectMemberRequest {
    Long project_id;
    Long workplace_member_id;
    String role;
}
