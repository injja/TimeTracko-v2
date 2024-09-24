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
    @Autowired
    public WorkspaceMemberService(WorkspaceMemberRepository workspaceMemberRepository, UserService userService, WorkspaceService workspaceService){
        super(workspaceMemberRepository);
        this.workspaceMemberRepository=workspaceMemberRepository;
        this.userService = userService;
        this.workspaceService = workspaceService;
    }

    public WorkspaceMember create(WorkspaceMemberRequest memberRequest){
        WorkspaceMember newMember=new WorkspaceMember();
        newMember.setRole(memberRequest.getRole());

        newMember.setUser(userService.findById(memberRequest.getUser_id()));

        newMember.setWorkspace(workspaceService.findById(memberRequest.getWorkspace_id()));
        System.out.println(newMember);
        return workspaceMemberRepository.save(newMember);
    }


}
