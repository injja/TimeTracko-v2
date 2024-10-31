package pl.api.timetracko.config.securityModels;

import jakarta.persistence.*;
import lombok.Data;
import pl.api.timetracko.models.Base;
import pl.api.timetracko.models.WorkspaceMember;

@Table(name="roles_granted")
@Data
@Entity
public class RolesGranted extends Base {

    @Id
    private Long id;

    @JoinColumn(name="role_id")
    @ManyToOne
    private Role role;

    @JoinColumn(name="workspace_member_id")
    @ManyToOne
    private WorkspaceMember workspaceMember;

}
