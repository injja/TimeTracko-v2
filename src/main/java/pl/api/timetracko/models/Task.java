package pl.api.timetracko.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties("tasks")
    private ProjectMember takenBy= null;

    @ManyToOne
    @JoinColumn(name="created-by")
    @JsonIgnoreProperties("tasks")
    private ProjectMember createdBy;

    @ManyToOne
    @JoinColumn(name="done-by")
    @JsonIgnoreProperties("tasks")
    private ProjectMember doneBy=null;

    @Column(nullable = false)
    @Min(1)
    @Max(10)
    private int priority;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime dueTo;

    @Column
    private LocalDateTime completed=null;


    @ManyToOne
    @JoinColumn(name="work-period")
    private WorkPeriod workPeriod;

    @ManyToOne
    @JoinColumn(name="project")
    @JsonIgnoreProperties("tasks")
    private Project project;












}
