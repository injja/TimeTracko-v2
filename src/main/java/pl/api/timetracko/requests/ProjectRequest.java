package pl.api.timetracko.requests;

import lombok.Data;

import java.util.List;

@Data
public class ProjectRequest {
    String name;
    String description;
    //List<ProjectMemberRequest> members;//???
    Long workspace_id;

}
