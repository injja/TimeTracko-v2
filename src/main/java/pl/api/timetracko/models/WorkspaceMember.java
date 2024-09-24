package pl.api.timetracko.models;

import jakarta.persistence.*;
import lombok.Data;
import pl.api.timetracko.Enums.Role;

@Data
@Entity
@Table(name="workspace-members")
public class WorkspaceMember extends Base {

    @JoinColumn(name="user_id", nullable = false)
    @ManyToOne
    private User user;

    @JoinColumn(name="workspace_id", nullable = false)
    @ManyToOne
    private Workspace workspace;

    @Column
    private boolean active=true;

    @Column
    private Role role;
}
