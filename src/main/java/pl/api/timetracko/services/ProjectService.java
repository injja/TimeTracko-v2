package pl.api.timetracko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.api.timetracko.models.Project;
import pl.api.timetracko.repositories.ProjectRepository;
import pl.api.timetracko.requests.ProjectRequest;

@Service
public class ProjectService extends CrudService<Project> {
    protected ProjectRepository projectRepository;
    @Autowired
    public ProjectService(ProjectRepository projectRepository){
        super(projectRepository);
        this.projectRepository=projectRepository;
    }

    public Project create(ProjectRequest projectRequest){
        Project newProject= new Project();
        newProject.setTitle(projectRequest.getName());
        newProject.setDescription(projectRequest.getDescription());
        // newProject.setCreatedBy();
        // newProject.setWorkspace();
        return projectRepository.save(newProject);
    }
}
