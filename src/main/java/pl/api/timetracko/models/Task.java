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
    private User takenBy;

    @ManyToOne
    @JoinColumn(name="created-by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name="done-by")
    private User doneBy;

    @Column(nullable = false)
    @Min(1)
    @Max(10)
    private int priority;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime dueTo;

    @Column
    private LocalDateTime completed;

    @ManyToOne
    @JoinColumn(name="work-period")
    private WorkPeriod workPeriod;

    @ManyToOne
    @JoinColumn(name="project")
    private Project project;












}
