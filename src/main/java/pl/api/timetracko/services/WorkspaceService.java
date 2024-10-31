package pl.api.timetracko.services;

import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.api.timetracko.config.securityServices.CustomUserDetailsService;
import pl.api.timetracko.models.Workspace;
import pl.api.timetracko.models.WorkspaceMember;
import pl.api.timetracko.repositories.WorkspaceMemberRepository;
import pl.api.timetracko.repositories.WorkspaceRepository;
import pl.api.timetracko.requests.WorkspaceMemberRequest;
import pl.api.timetracko.requests.WorkspaceRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkspaceService extends CrudService<Workspace> {
    private final WorkspaceMemberRepository workspaceMemberRepository;
    protected WorkspaceRepository workspaceRepository;
    protected CustomUserDetailsService customUserDetailsService;
    protected RoleService roleService;

    public WorkspaceService(WorkspaceRepository workspaceRepository, CustomUserDetailsService customUserDetailsService,  RoleService roleService, WorkspaceMemberRepository workspaceMemberRepository){
        super(workspaceRepository);
        this.workspaceRepository=workspaceRepository;
        this.customUserDetailsService=customUserDetailsService;
       this.workspaceMemberRepository=workspaceMemberRepository;
       this.roleService=roleService;
    }

    public Workspace create(Workspace workspace){
        workspace.setCreatedBy(customUserDetailsService.getCurrentUser().getUser());
        WorkspaceMember workspaceMember = new WorkspaceMember();
        workspaceMember.setRole(roleService.findById(1L));
        workspaceMember.setUser(customUserDetailsService.getCurrentUser().getUser());
        Workspace createdWorkspace=workspaceRepository.save(workspace);
        workspaceMember.setWorkspace(createdWorkspace);
        workspaceMemberRepository.save(workspaceMember);
        return createdWorkspace;
    }


    public List<Workspace> getAll(){
        return workspaceRepository.findAll().stream()
                .filter(workspace -> workspace.getWorkspaceMembers().stream()
                        .anyMatch(member -> member.getUser().getId().equals(customUserDetailsService.getCurrentUser().getUser().getId())))
                .collect(Collectors.toList());
}}
