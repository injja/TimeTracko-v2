package pl.api.timetracko.services;

import org.springframework.stereotype.Service;
import pl.api.timetracko.config.securityServices.CustomUserDetailsService;
import pl.api.timetracko.models.Project;
import pl.api.timetracko.models.Task;
import pl.api.timetracko.repositories.ProjectRepository;
import pl.api.timetracko.repositories.TaskRepository;
import pl.api.timetracko.requests.TaskRequest;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService extends CrudService<Task> {
    private final ProjectMemberService projectMemberService;
    protected TaskRepository taskRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final ProjectService projectService;
    protected ProjectRepository projectRepository;

    public TaskService(ProjectRepository projectRepository,TaskRepository taskRepository, CustomUserDetailsService customUserDetailsService, ProjectService projectService, ProjectMemberService projectMemberService){
        super(taskRepository);
        this.taskRepository=taskRepository;
        this.customUserDetailsService=customUserDetailsService;
        this.projectService=projectService;
        this.projectMemberService = projectMemberService;
        this.projectRepository = projectRepository;
    }

    public Task create(TaskRequest taskRequest){
        Task newTask=new Task();
        newTask.setName(taskRequest.getName());

        newTask.setCreated(LocalDateTime.now());
        newTask.setDescription(taskRequest.getDescription());
        newTask.setPriority(taskRequest.getPriority());
        newTask.setDueTo(taskRequest.getDueTo());
        newTask.setProject(projectService.findById(taskRequest.getProject_id()));
        newTask.setCreatedBy(projectMemberService.findByProjectIdAndUserId(taskRequest.getProject_id(), customUserDetailsService.getCurrentUser().getUser().getId()));
        return taskRepository.save(newTask);
    }

    public List<Task> getAll(Long project_id){
        return taskRepository.findAllByProjectId(project_id);
    }

    public Task update(TaskRequest taskRequest, Long id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setName(taskRequest.getName());
            task.setDescription(taskRequest.getDescription());
            task.setPriority(taskRequest.getPriority());
            task.setDueTo(taskRequest.getDueTo());
            return taskRepository.save(task);
        }else{
            throw new RuntimeException("Task with id " + id + " not found");
        }
    }

    public void complete(Long id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompleted(LocalDateTime.now());
            task.setDoneBy(projectMemberService.findByProjectIdAndUserId(task.getProject().getId(), customUserDetailsService.getCurrentUser().getUser().getId()));
            taskRepository.save(task);
        }else{
            throw new RuntimeException("Task with id " + id + " not found");
        }
    }

    public void assign(Long taskId, Long userId){
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTakenBy(projectMemberService.findByProjectIdAndUserId(task.getProject().getId(), userId));
            taskRepository.save(task);
        }else{
            throw new RuntimeException("Task with id " + taskId + " not found");
        }
    }

    public void unassign(Long taskId){
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTakenBy(null);
            taskRepository.save(task);
        }else{
            throw new RuntimeException("Task with id " + taskId + " not found");
        }
    }

    public void assign(Long taskId){
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTakenBy(projectMemberService.findByProjectIdAndUserId(task.getProject().getId(), customUserDetailsService.getCurrentUser().getUser().getId()));
            taskRepository.save(task);
        }else{
            throw new RuntimeException("Task with id " + taskId + " not found");
        }
    }

    public boolean isAssigned(Long taskId){
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            return task.getTakenBy().getWorkspaceMember().getUser().getId().equals(customUserDetailsService.getCurrentUser().getUser().getId());
        }else{
            throw new RuntimeException("Task with id " + taskId + " not found");
        }
    }

    public List<Task> urgentTasks(Long projectId){
        return taskRepository.findAllByProjectIdAndCompletedIsNull(projectId)
            .stream().filter(task -> task.getDueTo().isBefore(LocalDateTime.now().plusDays(1))).toList();
    }

    public List<Task> myUrgentTasks(){
        return taskRepository.findAllByTakenByIdAndCompletedIsNull(customUserDetailsService.getCurrentUser().getUser().getId())
            .stream().filter(task -> task.getDueTo().isBefore(LocalDateTime.now().plusDays(1)))
                .sorted(Comparator.comparing(Task::getPriority))
                .toList();
    }

    public List<Task> myUrgentTasks(Long projectId){
        return taskRepository.findAllByProjectIdAndTakenByIdAndCompletedIsNull(projectId, customUserDetailsService.getCurrentUser().getUser().getId())
            .stream().filter(task -> task.getDueTo().isBefore(LocalDateTime.now().plusDays(1)))
                .sorted(Comparator.comparing(Task::getPriority))
                .toList();
    }

    public boolean isAdmin(Long id) {
        Task task=taskRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No task with such id"));
        Project project=projectRepository.findById(task.getProject().getId())
                .orElseThrow(()->new RuntimeException("No project with such id"));
        return project.getProjectMembers().stream()
                .anyMatch(member -> member.getWorkspaceMember().getUser().getId().equals(customUserDetailsService.getCurrentUser().getUser().getId()) && member.getRole().getName().equals("admin"));
    }

    public boolean isMember(Long id) {
        Task task=taskRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No task with such id"));
        Project project=projectRepository.findById(task.getProject().getId())
                .orElseThrow(()->new RuntimeException("No project with such id"));
        return project.getProjectMembers().stream()
                .anyMatch(member -> member.getWorkspaceMember().getUser().getId().equals(customUserDetailsService.getCurrentUser().getUser().getId()));
    }

}
