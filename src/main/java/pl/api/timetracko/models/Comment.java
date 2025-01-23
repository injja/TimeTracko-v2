package pl.api.timetracko.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Table(name="comments")
@Data
@Entity
public class Comment extends Base{
    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name="task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name="created_by")
    private ProjectMember createdBy;

    @Column
    private LocalDateTime created=LocalDateTime.now();
}
