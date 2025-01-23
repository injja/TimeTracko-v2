package pl.api.timetracko.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.api.timetracko.models.WorkspaceMember;
import pl.api.timetracko.requests.WorkspaceMemberRequest;
import pl.api.timetracko.services.WorkspaceMemberService;

@RequestMapping("/workspace-member")
@RestController
public class WorkspaceMemberController extends GroupMemberController<WorkspaceMember> {
    protected WorkspaceMemberService workspaceMemberService;
    public WorkspaceMemberController(WorkspaceMemberService workspaceMemberService){
        super(workspaceMemberService);
        this.workspaceMemberService=workspaceMemberService;
    }

    @PostMapping
    @PreAuthorize("@workspaceService.isAdmin(#workspaceMemberRequest.workspace_id)")
    public ResponseEntity<WorkspaceMember> create(@RequestBody WorkspaceMemberRequest workspaceMemberRequest){
        WorkspaceMember createdMember=workspaceMemberService.create(workspaceMemberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }
}
