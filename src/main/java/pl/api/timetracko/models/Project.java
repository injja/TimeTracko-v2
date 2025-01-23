package pl.api.timetracko.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "projects")
public class Project extends Group {
//zmiany
//    @Column(name = "title")
//    private String title;
//
//    @Column(name = "description", length = 300)
//    private String description;
//
    @ManyToOne
    @JoinColumn(name = "created-by")
    @JsonIgnoreProperties("workspace")
    private WorkspaceMember createdBy;
//
    @OneToMany(mappedBy = "project", fetch=FetchType.LAZY ,cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("project")
    private List<Task> tasks;

    @JsonIgnoreProperties("project")
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<ProjectMember> projectMembers;

    @ManyToOne
    @JoinColumn(name="workspace_id")
    @JsonIgnoreProperties("project")
    private Workspace workspace;
//


}
