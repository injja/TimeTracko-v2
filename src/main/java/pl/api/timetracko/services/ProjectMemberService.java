package pl.api.timetracko.services;

import org.springframework.stereotype.Service;
import pl.api.timetracko.config.securityServices.CustomUserDetailsService;
import pl.api.timetracko.models.ProjectMember;
import pl.api.timetracko.repositories.ProjectMemberRepository;
import pl.api.timetracko.repositories.WorkspaceMemberRepository;
import pl.api.timetracko.requests.ProjectMemberRequest;

@Service
public class ProjectMemberService extends CrudService<ProjectMember> {
    protected ProjectMemberRepository projectMemberRepository;
    protected RoleService roleService;
    protected CustomUserDetailsService customUserDetailsService;
    protected ProjectService projectService;
    protected UserService userService;;
    public ProjectMemberService(UserService userService,ProjectService projectService, ProjectMemberRepository projectMemberRepository, RoleService roleService, CustomUserDetailsService customUserDetailsService){
        super(projectMemberRepository);
        this.projectMemberRepository = projectMemberRepository;
        this.roleService=roleService;
        this.customUserDetailsService=customUserDetailsService;
        this.projectService=projectService;
        this.userService=userService;
    }

    public ProjectMember create(ProjectMemberRequest memberRequest){
        ProjectMember newMember=new ProjectMember();
        newMember.setRole(roleService.findById(memberRequest.getRole_id()));
        newMember.setUser(userService.findById(memberRequest.getUser_id()));
        newMember.setProject(projectService.findById(memberRequest.getProject_id()));
        return projectMemberRepository.save(newMember);
    }
}
