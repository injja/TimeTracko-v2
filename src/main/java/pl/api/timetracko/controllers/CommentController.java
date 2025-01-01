package pl.api.timetracko.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.api.timetracko.models.Comment;
import pl.api.timetracko.requests.CommentRequest;
import pl.api.timetracko.services.CommentService;
import pl.api.timetracko.services.CrudService;
import pl.api.timetracko.services.TaskService;

@RestController
@RequestMapping("/comment")
public class CommentController extends CrudController<Comment>{
    protected CommentService commentService;
    protected TaskService taskService;
    public CommentController(CommentService commentService, TaskService taskService) {
        super(commentService);
        this.taskService=taskService;
    }

    @PostMapping
    @PreAuthorize("@taskService.isMember(commentRequest.getTask_id())")
    public ResponseEntity<Comment> create(@RequestBody CommentRequest commentRequest){
        Comment newComment= commentService.create(commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newComment);
    }
}
