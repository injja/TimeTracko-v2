package pl.api.timetracko.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import pl.api.timetracko.models.Project;
import pl.api.timetracko.services.ProjectService;
@RequestMapping("/project")
public class ProjectController extends BaseController<Project>{
    protected ProjectService projectService;
    public ProjectController(ProjectService projectService){
        super(projectService);
        this.projectService=projectService;
    }

}
