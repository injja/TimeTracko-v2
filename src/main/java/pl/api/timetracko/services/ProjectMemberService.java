package pl.api.timetracko.services;

import org.springframework.stereotype.Service;
import pl.api.timetracko.config.securityServices.CustomUserDetailsService;
import pl.api.timetracko.models.ProjectMember;
import pl.api.timetracko.repositories.GroupMemberRepository;
import pl.api.timetracko.repositories.ProjectMemberRepository;
import pl.api.timetracko.repositories.WorkspaceMemberRepository;
import pl.api.timetracko.requests.ProjectMemberRequest;



@Service
public class ProjectMemberService extends GroupMemberService<ProjectMember> {
    private final GroupMemberRepository groupMemberRepository;
    private final WorkspaceMemberService workspaceMemberService;
    protected ProjectMemberRepository projectMemberRepository;
    protected RoleService roleService;
    protected CustomUserDetailsService customUserDetailsService;
    protected ProjectService projectService;
    protected UserService userService;

    public ProjectMemberService(UserService userService, ProjectService projectService, ProjectMemberRepository projectMemberRepository, RoleService roleService, CustomUserDetailsService customUserDetailsService, GroupMemberRepository<ProjectMember> groupMemberRepository, WorkspaceMemberService workspaceMemberService){
        super(projectMemberRepository, groupMemberRepository);
        this.projectMemberRepository = projectMemberRepository;
        this.roleService=roleService;
        this.customUserDetailsService=customUserDetailsService;
        this.projectService=projectService;
        this.userService=userService;
        this.groupMemberRepository = groupMemberRepository;
        this.workspaceMemberService = workspaceMemberService;
    }

    public ProjectMember create(ProjectMemberRequest memberRequest){
        ProjectMember newMember=new ProjectMember();
        newMember.setRole(roleService.findById(memberRequest.getRole_id()));
        newMember.setWorkspaceMember(workspaceMemberService.findById(memberRequest.getWorkspace_member_id()));

        newMember.setProject(projectService.findById(memberRequest.getProject_id()));
        return projectMemberRepository.save(newMember);
    }

    public ProjectMember findByProjectIdAndUserId(Long projectId, Long id) {
        Long workspaceMemberId=workspaceMemberService.findByWorkspaceIdAndUserId(projectService.findById(projectId).getWorkspace().getId(),id).getId();
        return projectMemberRepository.findByWorkspaceMemberIdAndProjectId(workspaceMemberId, projectId);
    }



}
