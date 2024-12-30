package pl.api.timetracko.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@MappedSuperclass
@Data
public abstract class Group extends Base{
    @Id
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

//    @ManyToOne
//    @JoinColumn(name = "created-by")
//    private GroupMember createdBy;
//
//    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
//    private List<GroupMember> groupMembers;


}
