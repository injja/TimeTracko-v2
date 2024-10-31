package pl.api.timetracko.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.api.timetracko.config.securityModels.Role;
import pl.api.timetracko.services.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController extends CrudController<Role> {
    protected RoleService roleService;
    public RoleController(RoleService roleService) {
        super(roleService);
        this.roleService=roleService;
    }
}
