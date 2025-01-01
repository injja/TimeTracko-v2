package pl.api.timetracko.repositories;

import pl.api.timetracko.models.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment>{
    List<Comment> findByTaskId(Long taskId);
}
