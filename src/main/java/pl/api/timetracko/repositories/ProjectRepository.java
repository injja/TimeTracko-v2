package pl.api.timetracko.repositories;

import org.springframework.stereotype.Repository;
import pl.api.timetracko.models.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project>{
    List<Project> findAllByWorkspaceId(Long id);
//    Project findByUserIdAndWorkspaceId(Long userId, Long workspaceId);
}
