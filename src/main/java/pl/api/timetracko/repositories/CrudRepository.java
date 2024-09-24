package pl.api.timetracko.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.api.timetracko.models.Base;

import java.util.Optional;

@Repository
public interface CrudRepository<T extends Base> extends JpaRepository<T, Long> {
}
