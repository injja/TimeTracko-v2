package pl.api.timetracko.requests;

import lombok.Data;
import pl.api.timetracko.Enums.Role;
@Data
public class WorkspaceMemberRequest {
    Long user_id;
    Long workspace_id;
    Role role;
    public WorkspaceMemberRequest() {
        // Konstruktor bezargumentowy
    }
}
