package pl.api.timetracko.services;

import pl.api.timetracko.models.Base;
import pl.api.timetracko.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

public class BaseService<T extends Base> {
    protected CrudRepository<T> crudRepository;

    public BaseService(CrudRepository<T> crudRepository) {
        this.crudRepository = crudRepository;
    }

    public List<T> getAll(){
        return crudRepository.findAll();
    }

    public T findById(Long id){
        Optional<T> object = crudRepository.findById(id);
        if (object.isPresent()) {
            return object.get();
        } else {
            throw new RuntimeException("Object with id " + id + " not found");
        }
    }
    public void delete(Long id){
        crudRepository.deleteById(id);
    }
}
