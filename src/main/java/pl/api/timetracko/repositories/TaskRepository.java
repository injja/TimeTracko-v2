package pl.api.timetracko.repositories;

import org.springframework.stereotype.Repository;
import pl.api.timetracko.models.Task;
@Repository
public interface TaskRepository extends CrudRepository<Task> {
}
