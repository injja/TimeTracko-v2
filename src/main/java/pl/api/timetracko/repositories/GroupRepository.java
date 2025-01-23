package pl.api.timetracko.repositories;

import org.springframework.stereotype.Repository;
import pl.api.timetracko.models.Group;

@Repository
public interface GroupRepository<P extends Group> extends CrudRepository<Group> {
//    P findByUserIdAndGroupId(Long userId, Long groupId);
}
