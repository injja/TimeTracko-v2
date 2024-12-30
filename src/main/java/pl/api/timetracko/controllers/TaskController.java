package pl.api.timetracko.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.api.timetracko.models.Task;
import pl.api.timetracko.requests.AssignRequest;
import pl.api.timetracko.requests.TaskRequest;
import pl.api.timetracko.services.TaskService;

import java.util.List;
@RequestMapping("/task")
@RestController
public class TaskController extends BaseController<Task> {
    protected TaskService taskService;
    public TaskController(TaskService taskService) {
        super(taskService);
        this.taskService = taskService;
    }

    @GetMapping("/project/{project_id}")
    @PreAuthorize("@projectService.isMember(#project_id)")
    public ResponseEntity<List<Task>> findAllByProject(@PathVariable Long project_id) {
        return ResponseEntity.ok(taskService.getAll(project_id));
    }

    @PostMapping
    @PreAuthorize("@projectService.isMember(#task.getProject_id())")
    public ResponseEntity<Task> create(@RequestBody TaskRequest task) {
        Task createdObject = taskService.create(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdObject);
    }

    @PostMapping("/complete/{id}")
    @PreAuthorize("@taskService.isAssigned(#id)")
    public ResponseEntity<?> complete(@PathVariable Long id) {
        taskService.complete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/selfassign/{id}")
    @PreAuthorize("@projectService.isMember(#task.getProject_id())")
    public ResponseEntity<?> selfAssign(@PathVariable Long taskId) {
        taskService.assign(taskId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/assign/{id}")
    @PreAuthorize("@taskService.isAdmin(#id)")
    public ResponseEntity<?> assign(@PathVariable Long id, @RequestBody AssignRequest request) {
        taskService.assign(id, request.getUser_id());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("projectService.isMember(#task.getProject_id())")
    public ResponseEntity<Task> update(@RequestBody TaskRequest task, @PathVariable Long id) {
        Task updatedObject = taskService.update(task, id);
        return ResponseEntity.ok(updatedObject);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("projectService.isAdmin(#task.getProject_id())")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/myurgent/{project_id}")
    @PreAuthorize("@projectService.isMember(#project_id)")
    public ResponseEntity<List<Task>> getMyUrgentTasks(@PathVariable Long project_id) {
        return ResponseEntity.ok(taskService.myUrgentTasks(project_id));
    }

    @GetMapping("/myurgent")
    public ResponseEntity<List<Task>> getMyUrgentTasks() {
        return ResponseEntity.ok(taskService.myUrgentTasks());
    }

    @GetMapping("/urgent/{project_id}")
    @PreAuthorize("@projectService.isAdmin(#project_id)")
    public ResponseEntity<List<Task>> getUrgentTasks(@PathVariable Long project_id) {
        return ResponseEntity.ok(taskService.urgentTasks(project_id));
    }
    
 

}
