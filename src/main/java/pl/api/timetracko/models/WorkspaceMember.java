package pl.api.timetracko.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import pl.api.timetracko.config.securityModels.Role;
import pl.api.timetracko.config.securityModels.RolesGranted;

import java.util.List;

@Data
@Entity
@Table(name="workspace-members")
public class WorkspaceMember extends Base {

    @JoinColumn(name="user_id", nullable = false)
    @ManyToOne
    @JsonIgnoreProperties("workspaceMembers")
    private User user;

    @JoinColumn(name="workspace_id", nullable = false)
    @ManyToOne
    @JsonIgnoreProperties("workspaceMembers")
    private Workspace workspace;

    @Column
    private boolean active=true;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
//
//    @OneToMany(mappedBy = "workspaceMember", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("workspaceMember")
//    private List<RolesGranted> rolesGranted;
}
