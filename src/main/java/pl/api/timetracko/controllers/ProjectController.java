package pl.api.timetracko.controllers;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.api.timetracko.models.Project;
import pl.api.timetracko.requests.ProjectRequest;
import pl.api.timetracko.responses.MessageResponse;
import pl.api.timetracko.services.ProjectService;

import java.util.List;
@RestController
@RequestMapping("/projects")
@Primary
public class ProjectController extends GroupController<Project>{
    protected ProjectService projectService;
    public ProjectController(ProjectService projectService){
        super(projectService);
        this.projectService=projectService;
    }


    @GetMapping("/workspace/{workspace_id}")
    public ResponseEntity<List<Project>> findAll(@PathVariable Long workspace_id) {
        return ResponseEntity.ok(projectService.getAll(workspace_id));
    }

    @PostMapping
    @PreAuthorize("@workspaceService.isAdmin(#project.workspace_id)")
    public ResponseEntity<Project> create(@RequestBody ProjectRequest project){
        Project createdObject=projectService.create(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdObject);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@workspaceService.isAdmin(#project.workspace_id)")
    public ResponseEntity<Project> update(@PathVariable Long id, @RequestBody ProjectRequest project){
        Project updatedObject=projectService.update(project, id);
        return ResponseEntity.ok(updatedObject);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@workspaceService.isAdmin(#projectService.findById(id).getWorkspace().getId())")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id){
        projectService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("get/{id}")
    @PreAuthorize("@projectService.isMember(#id)")
    public ResponseEntity<Project> getById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findById(id));
    }
}
