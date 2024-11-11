package pl.api.timetracko.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.api.timetracko.config.securityServices.CustomUserDetailsService;
import pl.api.timetracko.models.Project;
import pl.api.timetracko.repositories.ProjectRepository;
import pl.api.timetracko.requests.ProjectRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService extends CrudService<Project> {
    private final WorkspaceService workspaceService;
    private final ProjectRepository projectRepository;
    private final CustomUserDetailsService customUserDetailsService;
    @Autowired
    public ProjectService(ProjectRepository projectRepository, CustomUserDetailsService customUserDetailsService, WorkspaceService workspaceService){
        super(projectRepository);
        this.projectRepository=projectRepository;
        this.customUserDetailsService=customUserDetailsService;
        this.workspaceService = workspaceService;
    }

    public Project create(ProjectRequest projectRequest){
        Project newProject= new Project();
        newProject.setTitle(projectRequest.getName());
        newProject.setDescription(projectRequest.getDescription());
        newProject.setWorkspace(workspaceService.findById(projectRequest.getWorkspace_id()));
        newProject.setCreatedBy(workspaceService.getMember(customUserDetailsService.getCurrentUser().getUser().getId(), projectRequest.getWorkspace_id()));
        return projectRepository.save(newProject);
    }

    public List<Project> getAll(Long workspaceId){
        if(workspaceService.isAdmin(workspaceId)){
            return projectRepository.findAll().stream()
                    .filter(project -> project.getWorkspace().getId().equals(workspaceId))
                    .collect(Collectors.toList());
        }
        else{
        return projectRepository.findAll().stream()
                .filter(project -> project.getProjectMembers().stream()
                        .anyMatch(member -> member.getUser().equals(customUserDetailsService.getCurrentUser().getUser()) && member.isActive()))
                .filter(project -> project.getWorkspace().getId().equals(workspaceId))
                .collect(Collectors.toList());
    }}
@Transactional
    public boolean isMember(Long projectId) {

        Project project=projectRepository.findById(projectId)
                .orElseThrow(()->new RuntimeException("No project with such id"));

    return project.getProjectMembers().stream()
            .peek(member -> System.out.println("Member: " + member.getUser().getId() + ", Current User: " + customUserDetailsService.getCurrentUser().getUser().getId()))
            .anyMatch(member -> member.getUser().getId().equals(customUserDetailsService.getCurrentUser().getUser().getId()));
}
}
