package pl.api.timetracko.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.api.timetracko.models.Task;
import pl.api.timetracko.requests.TaskRequest;
import pl.api.timetracko.services.TaskService;

import java.util.List;
@RequestMapping("/task")
@RestController
public class TaskController extends BaseController<Task> {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        super(taskService);
        this.taskService = taskService;
    }

    @GetMapping("/{project_id}")
    @PreAuthorize("@projectService.isMember(#project_id)")
    public ResponseEntity<List<Task>> findAllByProject(@PathVariable Long project_id) {
        return ResponseEntity.ok(taskService.getAll(project_id));
    }

    @PostMapping("/{project_id}")
    @PreAuthorize("@projectService.isMember(#project_id)")
    public ResponseEntity<Task> create(@RequestBody TaskRequest task, @PathVariable Long project_id) {
        System.out.println("test");
        Task createdObject = taskService.create(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdObject);
    }
}
