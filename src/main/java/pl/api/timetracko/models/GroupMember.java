package pl.api.timetracko.models;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import pl.api.timetracko.config.securityModels.Role;
@Data
@MappedSuperclass
public abstract class GroupMember extends Base{
//    @JoinColumn(name="user_id", nullable = false)
//    @ManyToOne
//    private User user;

    @Column
    private boolean active=true;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
}
