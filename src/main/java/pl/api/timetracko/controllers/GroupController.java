package pl.api.timetracko.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.api.timetracko.models.Group;
import pl.api.timetracko.models.Project;
import pl.api.timetracko.requests.ProjectRequest;
import pl.api.timetracko.services.BaseService;
import pl.api.timetracko.services.CrudService;
import pl.api.timetracko.services.GroupService;

@RestController
public abstract class GroupController<T extends Group> extends BaseController<T> {

    protected GroupService<T> groupService;

    public GroupController(GroupService<T> groupService) {
        super(groupService);
    }
}