package pl.api.timetracko.services;

import org.springframework.stereotype.Service;
import pl.api.timetracko.models.Workspace;
import pl.api.timetracko.repositories.WorkspaceRepository;
import pl.api.timetracko.requests.WorkspaceRequest;

@Service
public class WorkspaceService extends CrudService<Workspace> {
    protected WorkspaceRepository workspaceRepository;
    public WorkspaceService(WorkspaceRepository workspaceRepository){
        super(workspaceRepository);
        this.workspaceRepository=workspaceRepository;
    }

//    public Workspace create(Workspace workspace){
//        //create workspace member
//        return workspaceRepository.save(newWorkspace);
//    }
}
