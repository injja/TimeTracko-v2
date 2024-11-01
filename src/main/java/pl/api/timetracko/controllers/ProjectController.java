package pl.api.timetracko.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.api.timetracko.models.Project;
import pl.api.timetracko.requests.ProjectRequest;
import pl.api.timetracko.services.ProjectService;

import java.util.List;
@RestController
@RequestMapping("/project")
public class ProjectController extends BaseController<Project>{
    protected ProjectService projectService;
    public ProjectController(ProjectService projectService){
        super(projectService);
        this.projectService=projectService;
    }

    @GetMapping("/{workspace_id}")
    public ResponseEntity<List<Project>> findAll(@PathVariable Long workspace_id) {
        return ResponseEntity.ok(projectService.getAll(workspace_id));
    }

    @PostMapping
    @PreAuthorize("@workspaceService.isAdmin(#project.workspace_id)")
    public ResponseEntity<Project> create(@RequestBody ProjectRequest project){
        System.out.println(5);
        Project createdObject=projectService.create(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdObject);
    }
}
