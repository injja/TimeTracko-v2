package pl.api.timetracko.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import pl.api.timetracko.config.securityModels.Role;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="project-members")
public class ProjectMember extends GroupMember implements Serializable {

    @ManyToOne
    @JoinColumn(name="project_id")
    @JsonIgnoreProperties({"projectMembers", "tasks", "workspace","project"})
    private Project project;

    @ManyToOne
    @JoinColumn(name="workspace_member_id")
    @JsonIgnoreProperties("projectMembers")
    private WorkspaceMember workspaceMember;

    @OneToMany(mappedBy = "takenBy", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"takenBy", "createdBy", "doneBy"})
    private List<Task> tasksTaken;
//
    @OneToMany(mappedBy = "doneBy", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"takenBy", "createdBy", "doneBy"})
    private List<Task> tasksDone;
}
