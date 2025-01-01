package pl.api.timetracko.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import pl.api.timetracko.models.ProjectMember;
import pl.api.timetracko.requests.ProjectMemberRequest;
import pl.api.timetracko.services.BaseService;
import pl.api.timetracko.services.ProjectMemberService;
/**
 * Controller for managing project members.
 * This controller provides endpoints for creating and managing project members.
 * It extends the GroupMemberController to inherit common functionality.
 *
 * @see GroupMemberController
 * @see ProjectMemberService
 */

@RestController
@RequestMapping("/project-member")
public class ProjectMemberController extends GroupMemberController<ProjectMember> {
    private final ProjectMemberService projectMemberService;
    public ProjectMemberController(ProjectMemberService projectMemberService) {
        super(projectMemberService);
        this.projectMemberService=projectMemberService;
    }
    /**
     * Creates a new project member.
     *
     * @param projectMemberRequest the request object containing the details of the project member to be created
     * @return ResponseEntity containing the created ProjectMember and HTTP status code
     */
    @PostMapping
    @PreAuthorize("@projectService.isAdmin(projectMemberRequest.getProject_id())")
    public ResponseEntity<ProjectMember> create(@RequestBody ProjectMemberRequest projectMemberRequest){
        ProjectMember newMember=projectMemberService.create(projectMemberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMember);
    }
}
