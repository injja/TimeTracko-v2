package pl.api.timetracko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.api.timetracko.models.WorkspaceMember;
import pl.api.timetracko.repositories.GroupMemberRepository;
import pl.api.timetracko.repositories.WorkspaceMemberRepository;
import pl.api.timetracko.requests.WorkspaceMemberRequest;

@Service
public class WorkspaceMemberService extends GroupMemberService<WorkspaceMember> {
    private final GroupMemberRepository groupMemberRepository;
    protected WorkspaceMemberRepository workspaceMemberRepository;
    private UserService userService;
    private WorkspaceService workspaceService;
    private RoleService roleService;

    @Autowired
    public WorkspaceMemberService(WorkspaceMemberRepository workspaceMemberRepository, UserService userService, WorkspaceService workspaceService, RoleService roleService, GroupMemberRepository<WorkspaceMember> groupMemberRepository){
        super(workspaceMemberRepository, groupMemberRepository);
        this.workspaceMemberRepository=workspaceMemberRepository;
        this.userService = userService;
        this.workspaceService = workspaceService;
        this.roleService = roleService;
        this.groupMemberRepository = groupMemberRepository;
    }

    public WorkspaceMember create(WorkspaceMemberRequest memberRequest){
        WorkspaceMember newMember=new WorkspaceMember();
        newMember.setRole(roleService.findById(memberRequest.getRole_id()));
        newMember.setUser(userService.findById(memberRequest.getUser_id()));
        newMember.setWorkspace(workspaceService.findById(memberRequest.getWorkspace_id()));
        return workspaceMemberRepository.save(newMember);
    }

    public WorkspaceMember findByWorkspaceIdAndUserId(Long workspaceId, Long id) {
        return workspaceMemberRepository.findByUserIdAndWorkspaceId(id, workspaceId);
    }





}
