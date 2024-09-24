package pl.api.timetracko.repositories;

import org.springframework.stereotype.Repository;
import pl.api.timetracko.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User>{
    Optional<User> findByEmail(String email);
    User findByUsername(String username);

}
