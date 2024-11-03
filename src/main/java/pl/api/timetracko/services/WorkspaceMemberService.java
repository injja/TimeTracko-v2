package pl.api.timetracko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.api.timetracko.models.WorkspaceMember;
import pl.api.timetracko.repositories.WorkspaceMemberRepository;
import pl.api.timetracko.requests.WorkspaceMemberRequest;

@Service
public class WorkspaceMemberService extends BaseService<WorkspaceMember> {
    protected WorkspaceMemberRepository workspaceMemberRepository;
    private UserService userService;
    private WorkspaceService workspaceService;
    private RoleService roleService;

    @Autowired
    public WorkspaceMemberService(WorkspaceMemberRepository workspaceMemberRepository, UserService userService, WorkspaceService workspaceService, RoleService roleService){
        super(workspaceMemberRepository);
        this.workspaceMemberRepository=workspaceMemberRepository;
        this.userService = userService;
        this.workspaceService = workspaceService;
        this.roleService = roleService;
    }

    public WorkspaceMember create(WorkspaceMemberRequest memberRequest){
        WorkspaceMember newMember=new WorkspaceMember();
        newMember.setRole(roleService.findById(memberRequest.getRole_id()));
        newMember.setUser(userService.findById(memberRequest.getUser_id()));
        newMember.setWorkspace(workspaceService.findById(memberRequest.getWorkspace_id()));
        return workspaceMemberRepository.save(newMember);
    }

    public WorkspaceMember desactivate(Long id){
        WorkspaceMember member = findById(id);
        member.setRole(roleService.findById(3L));
        member.setActive(false);
        return workspaceMemberRepository.save(member);
    }




}
