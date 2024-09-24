package pl.api.timetracko.repositories;

import org.springframework.stereotype.Repository;
import pl.api.timetracko.models.WorkspaceMember;
@Repository
public interface WorkspaceMemberRepository extends CrudRepository<WorkspaceMember> {
}
