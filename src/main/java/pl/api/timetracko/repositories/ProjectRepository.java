package pl.api.timetracko.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.api.timetracko.models.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project>{

}
