package pl.api.timetracko.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.api.timetracko.config.securityServices.CustomUserDetailsService;
import pl.api.timetracko.models.Project;
import pl.api.timetracko.models.Workspace;
import pl.api.timetracko.models.WorkspaceMember;
import pl.api.timetracko.repositories.*;
import pl.api.timetracko.requests.WorkspaceMemberRequest;
import pl.api.timetracko.requests.WorkspaceRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkspaceService extends GroupService<Workspace> {
    protected WorkspaceMemberRepository workspaceMemberRepository;
    protected WorkspaceRepository workspaceRepository;
    protected CustomUserDetailsService customUserDetailsService;
    protected RoleService roleService;
    protected ProjectMemberRepository projectMemberRepository;
    protected GroupMemberRepository<WorkspaceMember> groupMemberRepository;
    protected ProjectRepository projectRepository;

    public WorkspaceService(ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository,WorkspaceRepository workspaceRepository, CustomUserDetailsService customUserDetailsService,  RoleService roleService, WorkspaceMemberRepository workspaceMemberRepository){
        super(workspaceRepository);
        this.workspaceRepository=workspaceRepository;
        this.customUserDetailsService=customUserDetailsService;
        this.workspaceMemberRepository=workspaceMemberRepository;
        this.roleService=roleService;
        this.projectRepository=projectRepository;
        this.projectMemberRepository=projectMemberRepository;
    }


    public Workspace create(WorkspaceRequest workspace){
        Workspace newWorkspace=new Workspace();
        newWorkspace.setTitle(workspace.getName());
        newWorkspace.setDescription(workspace.getDescription());
        System.out.println("workspace.getName() = " + workspace.getName());
        Workspace createdWorkspace=workspaceRepository.save(newWorkspace);

        WorkspaceMember workspaceMember = new WorkspaceMember();
        workspaceMember.setRole(roleService.findById(1L));
        workspaceMember.setUser(customUserDetailsService.getCurrentUser().getUser());
        workspaceMember.setWorkspace(createdWorkspace);
        WorkspaceMember createdMember=workspaceMemberRepository.save(workspaceMember);

        newWorkspace.setCreatedBy(createdMember);
        return createdWorkspace;
    }


    public List<Workspace> getAll(){
        return workspaceRepository.findAll().stream()
                .filter(workspace -> workspace.getWorkspaceMembers().stream()
                        .anyMatch(member-> member.isActive()))
                .filter(workspace -> workspace.getWorkspaceMembers().stream()
                        .anyMatch(member -> member.getUser().getId().equals(customUserDetailsService.getCurrentUser().getUser().getId())))
                .collect(Collectors.toList());
}

        public WorkspaceMember getMember(Long userId, Long workspaceId){
            return workspaceMemberRepository.findByUserIdAndWorkspaceId(userId, workspaceId);
        }

        public boolean isAdmin(Long workspaceId){
        try {
            return workspaceMemberRepository.findByUserIdAndWorkspaceId(customUserDetailsService.getCurrentUser().getUser().getId(), workspaceId).getRole().getId().equals(1L);
        } catch (NullPointerException e){
            return false;

        }}

    public List<Workspace> getAllAdmin(){
        return workspaceRepository.findAll();
    }
@Transactional
    public void delete(Long id){
        Workspace workspace = workspaceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Workspace not found"));
        List<Project> projects=projectRepository.findAllByWorkspaceId(id);
        projects.forEach(project -> {
            projectMemberRepository.deleteAll(project.getProjectMembers());
            projectRepository.delete(project);
        });

        workspaceMemberRepository.deleteAllByWorkspaceId(id);

        workspaceRepository.delete(workspace);
    }

    public boolean isMember(Long id) {
        Workspace workspace=workspaceRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No workspace with such id"));
        return workspace.getWorkspaceMembers().stream()
                .anyMatch(member -> member.getUser().getId().equals(customUserDetailsService.getCurrentUser().getUser().getId()));
    }



}
