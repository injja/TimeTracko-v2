package pl.api.timetracko.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.api.timetracko.models.Workspace;
import pl.api.timetracko.services.WorkspaceService;

import java.util.List;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController extends CrudController<Workspace> {


    protected WorkspaceService workspaceService;
    public WorkspaceController(WorkspaceService workspaceService){
        super(workspaceService);
        this.workspaceService=workspaceService;
    }

    @GetMapping
    public ResponseEntity<List<Workspace>> findAll(){
        return ResponseEntity.ok(workspaceService.getAll());
    }


}
