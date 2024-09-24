package pl.api.timetracko.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import pl.api.timetracko.Enums.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name="users")
public class User extends Base {

    @Column(unique=true, nullable=false)
    private String username;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String surname;

    @Column(nullable=false)
    private String email;

    @Column
    private String password;

    @Column
    private Role role;

//    public User(String email, String password, String username, String name, String surname) {
//        this.email = email;
//        this.password = password;
//        this.name=name;
//        this.surname=surname;
//        this.username=username;
//    }


//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
//    @JsonIgnoreProperties({"user"})
//    private List<WorkspaceMember> workspaceMembers;

}
