package pl.api.timetracko.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name="tasks")
public class Task extends Base {

    @Column(nullable=false)
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name="taken-by")
    private ProjectMember takenBy;

    @ManyToOne
    @JoinColumn(name="created-by", nullable = false)
    private ProjectMember createdBy;

    @ManyToOne
    @JoinColumn(name="done-by")
    private ProjectMember doneBy;

    @Column(nullable = false)
    @Min(1)
    @Max(10)
    private int priority;

    @Column
    private LocalDate created;

    @Column
    private LocalDate dueTo;

    @Column
    private LocalDate completed;

    @ManyToOne
    @JoinColumn(name="work-period")
    private WorkPeriod workPeriod;

    @ManyToOne
    @JoinColumn(name="project")
    private Project project;












}
