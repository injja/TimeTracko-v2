package pl.api.timetracko.requests;

import lombok.Data;

import java.util.List;

@Data
public class ProjectRequest {
    String name;
    String description;
    Long workspace_id;
}
