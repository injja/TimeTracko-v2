package pl.api.timetracko.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name="workdays")
public class Workday extends Base {

    @OneToMany(mappedBy = "workday", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("work-period")
    private List<WorkPeriod> workPeriods;


    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="user")
    private User user;
}