package pl.api.timetracko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.api.timetracko.config.securityModels.RolesGranted;
import pl.api.timetracko.repositories.RolesGrantedRepository;

@Service
public class RolesGrantedService extends CrudService<RolesGranted> {
    private final RolesGrantedRepository rolesGrantedRepository;

    @Autowired
    public RolesGrantedService(RolesGrantedRepository rolesGrantedRepository){
        super(rolesGrantedRepository);
        this.rolesGrantedRepository=rolesGrantedRepository;
    }
}
