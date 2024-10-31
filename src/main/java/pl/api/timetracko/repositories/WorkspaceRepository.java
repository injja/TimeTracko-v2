package pl.api.timetracko.repositories;

import org.springframework.stereotype.Repository;
import pl.api.timetracko.models.Workspace;

import java.util.List;

@Repository
public interface WorkspaceRepository extends CrudRepository<Workspace> {

}
