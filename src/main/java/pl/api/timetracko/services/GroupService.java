package pl.api.timetracko.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.api.timetracko.models.Group;
import pl.api.timetracko.repositories.CrudRepository;
import pl.api.timetracko.repositories.GroupRepository;
@Service
public class GroupService<T extends Group> extends BaseService<T> {

    protected CrudRepository<T> crudRepository;

    public GroupService(@Qualifier("groupRepository") CrudRepository<T> crudRepository) {
        super(crudRepository);
        this.crudRepository = crudRepository;
    }

    public T create(T t) {
        return crudRepository.save(t);
    }


}