package pl.api.timetracko.repositories;

import org.springframework.stereotype.Repository;
import pl.api.timetracko.config.securityModels.Role;
@Repository
public interface RoleRepository extends CrudRepository<Role>{

}
