package pl.api.timetracko.repositories;

import org.springframework.stereotype.Repository;
import pl.api.timetracko.models.ProjectMember;

@Repository
public interface ProjectMemberRepository extends CrudRepository<ProjectMember> {

    ProjectMember findByWorkspaceMemberIdAndProjectId(Long id, Long projectId);

    void deleteAllByProjectId(Long id);
}
