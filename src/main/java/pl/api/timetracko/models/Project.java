package pl.api.timetracko.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="projects")
public class Project extends Base{

    @Column(name="title")
    private String title;

    @Column(name="description", length = 300)
    private String description;

    @ManyToOne
    @JoinColumn(name="created-by")
    private WorkspaceMember createdBy;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("project")
    private List<Task> tasks;

//    @ManyToOne
//    @JoinColumn(name="workspace")
//    private Workspace workspace;





}
