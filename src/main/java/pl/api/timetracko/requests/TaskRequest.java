package pl.api.timetracko.requests;

import jakarta.validation.constraints.Max;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {
    String name;
    String description;
    Long project_id;
    int priority;
    LocalDate due_date;

}
