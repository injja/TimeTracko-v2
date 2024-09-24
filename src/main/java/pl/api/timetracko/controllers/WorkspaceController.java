package pl.api.timetracko.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.api.timetracko.models.Workspace;
import pl.api.timetracko.services.WorkspaceService;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController extends CrudController<Workspace> {
    protected WorkspaceService workspaceService;
    public WorkspaceController(WorkspaceService workspaceService){
        super(workspaceService);
        this.workspaceService=workspaceService;
    }


}
