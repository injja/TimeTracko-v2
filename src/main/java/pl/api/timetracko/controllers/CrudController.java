package pl.api.timetracko.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.api.timetracko.models.Base;
import pl.api.timetracko.models.WorkspaceMember;
import pl.api.timetracko.requests.WorkspaceMemberRequest;
import pl.api.timetracko.responses.MessageResponse;
import pl.api.timetracko.services.CrudService;

import java.util.List;

@RestController
public abstract class CrudController<T extends Base> extends BaseController<T>{
    protected final CrudService<T> crudService;
    public CrudController(CrudService<T> crudService) {
        super(crudService);
        this.crudService = crudService;
    }
    @PreAuthorize("@userService.isAdmin()")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable long id) {
        crudService.delete(id);
        return ResponseEntity.status(250).body(new MessageResponse("Prawidlowo usunięto"));
    }
    @PreAuthorize("@userService.isAdmin()")
    @PostMapping
    public ResponseEntity<T> create(@RequestBody T t){
        T createdObject=crudService.create(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdObject);
    }
    @PreAuthorize("@userService.isAdmin()")
    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable Long id, @RequestBody T t){
        T updatedObject=crudService.update(id, t);
        return ResponseEntity.ok(updatedObject);
    }

}
