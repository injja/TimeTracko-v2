package pl.api.timetracko.requests;

import lombok.Data;

@Data
public class CommentRequest {
    Long task_id;
    String content;
}
