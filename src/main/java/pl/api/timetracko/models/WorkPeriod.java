package pl.api.timetracko.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@Table(name="work-periods")
public class WorkPeriod extends Base{

    @Column
    private LocalDateTime start;

    @Column
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name="workday_id")
    private Workday workday;

    private long duration=(end==null ? 0 : ChronoUnit.MINUTES.between(start,end));
}
