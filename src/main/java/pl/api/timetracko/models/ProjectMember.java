package pl.api.timetracko.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="project-members")
public class ProjectMember extends Base {

    @Column
    private boolean active=true;

    @Column
    private String role;

    @ManyToOne
    @JoinColumn(name="workspace-member_id")
    private WorkspaceMember workspaceMember;




}
