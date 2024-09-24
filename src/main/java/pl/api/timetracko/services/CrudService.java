package pl.api.timetracko.services;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Service;
import pl.api.timetracko.models.Base;
import pl.api.timetracko.repositories.CrudRepository;
import pl.api.timetracko.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
@Service
public abstract class CrudService<T extends Base> extends BaseService<T> {
    protected CrudRepository<T> crudRepository;

    public CrudService(CrudRepository<T> crudRepository) {
        super(crudRepository);
        this.crudRepository = crudRepository;
    }


    public T create(T object){
        return crudRepository.save(object);
    }
    public T update(Long id,T newObject){
        newObject.setId(id);
        return crudRepository.save(newObject);
    }

}
