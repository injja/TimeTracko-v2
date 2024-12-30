package pl.api.timetracko.repositories;

import org.springframework.stereotype.Repository;
import pl.api.timetracko.models.WorkspaceMember;

import java.util.List;

@Repository
public interface WorkspaceMemberRepository extends CrudRepository<WorkspaceMember> {
    WorkspaceMember findByUserIdAndWorkspaceId(Long userId, Long workspaceId);

    List<WorkspaceMember> findByWorkspaceId(Long id);

    void deleteAllByWorkspaceId(Long id);
}
