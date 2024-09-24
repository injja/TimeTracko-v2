package pl.api.timetracko.models;

import jakarta.persistence.*;
import lombok.Data;
import pl.api.timetracko.Enums.Role;

@Data
@Entity
@Table(name="project-members")
public class ProjectMember extends Base {

    @Column
    private boolean active=true;

    @Column
    private Role role;

    @ManyToOne
    @JoinColumn(name="workspace-member_id")
    private WorkspaceMember workspaceMember;



}
