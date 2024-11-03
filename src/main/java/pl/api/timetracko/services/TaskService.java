package pl.api.timetracko.services;

import pl.api.timetracko.config.securityServices.CustomUserDetailsService;
import pl.api.timetracko.models.Task;
import pl.api.timetracko.repositories.TaskRepository;
import pl.api.timetracko.requests.TaskRequest;

import java.time.LocalDateTime;

public class TaskService extends CrudService<Task> {
    protected TaskRepository taskRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final ProjectService projectService;

    public TaskService(TaskRepository taskRepository, CustomUserDetailsService customUserDetailsService, ProjectService projectService){
        super(taskRepository);
        this.taskRepository=taskRepository;
        this.customUserDetailsService=customUserDetailsService;
        this.projectService=projectService;

    }

    public Task create(TaskRequest taskRequest){
        Task newTask=new Task();
        newTask.setName(taskRequest.getName());
        newTask.setCreatedBy(customUserDetailsService.getCurrentUser().getUser());
        newTask.setCreated(LocalDateTime.now());
        newTask.setDescription(taskRequest.getDescription());
        newTask.setPriority(taskRequest.getPriority());
        newTask.setDueTo(taskRequest.getDueTo());
        newTask.setProject(projectService.findById(taskRequest.getProject_id()));
        return taskRepository.save(newTask);
    }
}
