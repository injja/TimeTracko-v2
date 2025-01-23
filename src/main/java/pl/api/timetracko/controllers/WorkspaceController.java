package pl.api.timetracko.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.api.timetracko.models.Project;
import pl.api.timetracko.models.Workspace;
import pl.api.timetracko.requests.ProjectRequest;
import pl.api.timetracko.requests.WorkspaceRequest;
import pl.api.timetracko.responses.MessageResponse;
import pl.api.timetracko.services.WorkspaceService;

import java.util.List;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController extends GroupController<Workspace> {


    protected WorkspaceService workspaceService;
    public WorkspaceController(WorkspaceService workspaceService){
        super(workspaceService);
        this.workspaceService=workspaceService;
    }

    @PostMapping
    public ResponseEntity<Workspace> create(@RequestBody WorkspaceRequest workspace){
        Workspace createdObject=workspaceService.create(workspace);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdObject);
    }

    @GetMapping("/admin")
    @PreAuthorize("@userService.isAdmin()")
    public ResponseEntity<List<Workspace>> findAllAdmin(){
        return ResponseEntity.ok(workspaceService.getAllAdmin());
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("@workspaceService.isAdmin(#id)")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id){
        workspaceService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
