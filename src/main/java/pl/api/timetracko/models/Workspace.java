package pl.api.timetracko.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="workspaces")
public class Workspace extends Base {

    @Column(nullable=false)
    private String name;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("workspace")
    private List<WorkspaceMember> workspaceMembers;

    @ManyToOne
    @JoinColumn(name = "created-by")
    @JsonIgnoreProperties("workspaceMembers")
    private User createdBy;

}
