package pl.api.timetracko.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import pl.api.timetracko.config.securityModels.Role;

import java.util.List;

@Data
@Entity
@Table(name="project-members")
public class ProjectMember extends Base {

    @Column
    private boolean active=true;

    @ManyToOne
    @JoinColumn(name="workspace-member_id")
    private WorkspaceMember workspaceMember;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

//    @OneToMany(mappedBy = "takenBy", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("takenBy")
//    private List<Task> tasksTaken;
//
//    @OneToMany(mappedBy = "doneBy", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("doneBy")
//    private List<Task> tasksDone;
}
