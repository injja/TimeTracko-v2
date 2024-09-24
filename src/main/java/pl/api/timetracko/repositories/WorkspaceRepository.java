package pl.api.timetracko.repositories;

import org.springframework.stereotype.Repository;
import pl.api.timetracko.models.Workspace;
@Repository
public interface WorkspaceRepository extends CrudRepository<Workspace> {
}
