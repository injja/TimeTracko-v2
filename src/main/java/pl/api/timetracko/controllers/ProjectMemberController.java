package pl.api.timetracko.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.api.timetracko.models.ProjectMember;
import pl.api.timetracko.requests.ProjectMemberRequest;
import pl.api.timetracko.services.BaseService;
import pl.api.timetracko.services.ProjectMemberService;

@RestController
@RequestMapping("/project-member")
public class ProjectMemberController extends BaseController<ProjectMember> {
    private final ProjectMemberService projectMemberService;
    public ProjectMemberController(ProjectMemberService projectMemberService) {
        super(projectMemberService);
        this.projectMemberService=projectMemberService;
    }

    @PostMapping
    public ResponseEntity<ProjectMember> create(@RequestBody ProjectMemberRequest projectMemberRequest){
        ProjectMember newMember=projectMemberService.create(projectMemberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMember);
    }
}
