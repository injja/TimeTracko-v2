package pl.api.timetracko.requests;

import lombok.Data;

import java.util.List;
@Data
public class WorkspaceRequest {
    String name;
    String description;
    //List<WorkspaceMemberRequest> workspace_members;//????
}
