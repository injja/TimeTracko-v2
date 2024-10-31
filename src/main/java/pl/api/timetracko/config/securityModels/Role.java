package pl.api.timetracko.config.securityModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import pl.api.timetracko.models.Base;

@Data
@Table(name="roles")
@Entity
public class Role extends Base {
    @Id
    private Long id;

    @Column
    private String name;

    public Role() {
    }
}
