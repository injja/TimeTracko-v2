package pl.api.timetracko.requests;

import jakarta.validation.constraints.Max;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TaskRequest {
    String name;
    String description;
    Long project_id;
    int priority;
    LocalDateTime dueTo;


}
