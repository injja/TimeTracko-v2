package pl.api.timetracko.repositories;

import org.springframework.stereotype.Repository;
import pl.api.timetracko.models.GroupMember;
import pl.api.timetracko.models.WorkspaceMember;
@Repository
public interface GroupMemberRepository<P extends GroupMember> extends CrudRepository<GroupMember> {
 //   P findByUserIdAndGroupId(Long userId, Long groupId);
}
