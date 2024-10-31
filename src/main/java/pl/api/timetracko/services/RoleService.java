package pl.api.timetracko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.api.timetracko.config.securityModels.Role;
import pl.api.timetracko.repositories.RoleRepository;
import pl.api.timetracko.repositories.UserRepository;

@Service
public class RoleService extends CrudService<Role> {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        super(roleRepository);
        this.roleRepository=roleRepository;
    }
}
