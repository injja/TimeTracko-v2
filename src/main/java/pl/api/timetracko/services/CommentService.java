package pl.api.timetracko.services;

import org.springframework.stereotype.Service;
import pl.api.timetracko.config.securityServices.CustomUserDetailsService;
import pl.api.timetracko.models.Comment;
import pl.api.timetracko.models.ProjectMember;
import pl.api.timetracko.repositories.CommentRepository;
import pl.api.timetracko.repositories.CrudRepository;
import pl.api.timetracko.requests.CommentRequest;

@Service
public class CommentService extends CrudService<Comment> {
    private final CommentRepository commentRepository;
    protected TaskService taskService;
    protected CustomUserDetailsService customUserDetailsService;
    protected ProjectMemberService projectMemberService;

    public CommentService(ProjectMemberService projectMemberService, CommentRepository commentRepository, TaskService taskService, CustomUserDetailsService customUserDetailsService) {
        super(commentRepository);
        this.taskService=taskService;
        this.customUserDetailsService=customUserDetailsService;
        this.projectMemberService=projectMemberService;
        this.commentRepository = commentRepository;
    }

    public Comment create(CommentRequest request){
        Comment newComment= new Comment();
        newComment.setTask(taskService.findById(request.getTask_id()));
        newComment.setCreatedBy(projectMemberService.findByProjectIdAndUserId(newComment.getTask().getProject().getId(),customUserDetailsService.getCurrentUser().getUser().getId()));
        newComment.setContent(request.getContent());
        return commentRepository.save(newComment);
    }
}
