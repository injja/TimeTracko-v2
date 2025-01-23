package pl.api.timetracko.repositories;

import org.springframework.stereotype.Repository;
import pl.api.timetracko.models.Task;

import java.util.Collection;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task> {
    List<Task> findAllByProjectId(Long projectId);

    List<Task> findAllByCompletedIsNull();

    List<Task> findAllByProjectIdAndCompletedIsNull(Long ProjectId);

    List<Task> findAllByTakenByIdAndCompletedIsNull(Long id);

    List<Task> findAllByProjectIdAndTakenByIdAndCompletedIsNull(Long projectId, Long id);
}
