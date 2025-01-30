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
public class Workspace extends Group {
//zmiany
//    @Column(nullable=false)
//    private String name;
//
    @OneToMany(mappedBy = "workspace", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("workspaceMembers")
    private List<WorkspaceMember> workspaceMembers;

    @ManyToOne
    @JoinColumn(name = "created-by")
    @JsonIgnoreProperties({"workspace","workspaceMembers"})

    private WorkspaceMember createdBy;

}
