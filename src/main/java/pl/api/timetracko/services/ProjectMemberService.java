package pl.api.timetracko.services;

import org.springframework.stereotype.Service;
import pl.api.timetracko.models.ProjectMember;
import pl.api.timetracko.repositories.ProjectMemberRepository;
import pl.api.timetracko.requests.ProjectMemberRequest;

@Service
public class ProjectMemberService extends CrudService<ProjectMember> {
    protected ProjectMemberRepository projectMemberRepository;
    public ProjectMemberService(ProjectMemberRepository projectMemberRepository){
        super(projectMemberRepository);
        this.projectMemberRepository = projectMemberRepository;
    }

    public ProjectMember create(ProjectMemberRequest memberRequest){
        ProjectMember newMember=new ProjectMember();
        newMember.setRole(memberRequest.getRole());
        //newMember.setWorkspaceMember();
        return projectMemberRepository.save(newMember);
    }
}
