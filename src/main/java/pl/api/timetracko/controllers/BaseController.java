package pl.api.timetracko.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.api.timetracko.models.Base;
import pl.api.timetracko.responses.MessageResponse;
import pl.api.timetracko.services.BaseService;
import pl.api.timetracko.services.CrudService;

import java.util.List;

@RestController
public abstract class BaseController<T extends Base> {
    protected final BaseService<T> baseService;
    public BaseController(BaseService<T> baseService) {
        this.baseService = baseService;
    }

    @GetMapping
    public ResponseEntity<List<T>> findAll(){
        return ResponseEntity.ok(baseService.getAll());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<MessageResponse> delete(@PathVariable long id) {
//        baseService.delete(id);
//        return ResponseEntity.status(250).body(new MessageResponse("Prawidlowo usunięto"));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable Long id) {
        return ResponseEntity.ok(baseService.findById(id));
    }


}
