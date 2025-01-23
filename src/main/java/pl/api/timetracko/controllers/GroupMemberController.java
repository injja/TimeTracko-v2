package pl.api.timetracko.controllers;

import org.springframework.web.bind.annotation.RestController;
import pl.api.timetracko.models.GroupMember;
import pl.api.timetracko.services.BaseService;
@RestController
public abstract class GroupMemberController<T extends GroupMember> extends BaseController<T> {
    protected BaseService<T> baseService;
    public GroupMemberController(BaseService<T> baseService) {
        super(baseService);
    }
}
