package pl.api.timetracko.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.api.timetracko.config.securityServices.CustomUserDetailsService;
import pl.api.timetracko.models.Project;
import pl.api.timetracko.repositories.ProjectMemberRepository;
import pl.api.timetracko.repositories.ProjectRepository;
import pl.api.timetracko.requests.ProjectRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService extends GroupService<Project> {
    private final WorkspaceService workspaceService;
    private final ProjectRepository projectRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final ProjectMemberRepository projectMemberRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, CustomUserDetailsService customUserDetailsService, WorkspaceService workspaceService, ProjectMemberRepository projectMemberRepository){
        super(projectRepository);
        this.projectRepository=projectRepository;
        this.customUserDetailsService=customUserDetailsService;
        this.workspaceService = workspaceService;
        this.projectMemberRepository = projectMemberRepository;
    }

    public Project create(ProjectRequest projectRequest){
        Project newProject= new Project();
        newProject.setTitle(projectRequest.getName());
        newProject.setDescription(projectRequest.getDescription());
        newProject.setWorkspace(workspaceService.findById(projectRequest.getWorkspace_id()));
        newProject.setCreatedBy(workspaceService.getMember(customUserDetailsService.getCurrentUser().getUser().getId(), projectRequest.getWorkspace_id()));
        return projectRepository.save(newProject);
    }

    public Project update(ProjectRequest projectRequest, Long id){
        Project project=projectRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No project with such id"));
        project.setTitle(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        project.setWorkspace(workspaceService.findById(projectRequest.getWorkspace_id()));
        project.setCreatedBy(workspaceService.getMember(customUserDetailsService.getCurrentUser().getUser().getId(), projectRequest.getWorkspace_id()));
        return projectRepository.save(project);
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
                        .anyMatch(member -> member.getWorkspaceMember().getUser().equals(customUserDetailsService.getCurrentUser().getUser()) && member.isActive()))
                .filter(project -> project.getWorkspace().getId().equals(workspaceId))
                .collect(Collectors.toList());
    }}
    @Transactional
    public boolean isMember(Long projectId) {
        Project project=projectRepository.findById(projectId)
                .orElseThrow(()->new RuntimeException("No project with such id"));

        return project.getProjectMembers().stream()
                .filter(member -> member.getWorkspaceMember().getUser() != null)
                .peek(member -> System.out.println("Member: " + member.getWorkspaceMember().getUser().getId() + ", Current User: " + customUserDetailsService.getCurrentUser().getUser().getId()))
                .anyMatch(member -> member.getWorkspaceMember().getUser().getId().equals(customUserDetailsService.getCurrentUser().getUser().getId()));
    }
    public boolean isAdmin(Long projectId){
        System.out.println("Checking if user is admin");
        Project project=projectRepository.findById(projectId)
                .orElseThrow(()->new RuntimeException("No project with such id"));
        return project.getProjectMembers().stream()
            .anyMatch(member -> member.getWorkspaceMember().getUser().getId().equals(customUserDetailsService.getCurrentUser().getUser().getId()) && member.getRole().getName().equals("admin"));
    }

    public void delete(Long id){
        projectMemberRepository.deleteAllByProjectId(id);
        projectRepository.deleteById(id);
    }


}
