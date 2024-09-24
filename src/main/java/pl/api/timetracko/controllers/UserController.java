package pl.api.timetracko.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.api.timetracko.models.User;
import pl.api.timetracko.requests.UserRequest;
import pl.api.timetracko.responses.MessageResponse;
import pl.api.timetracko.services.CrudService;
import pl.api.timetracko.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends CrudController<User> {
    protected UserService userService;
    public UserController(UserService userService) {
        super(userService);
        this.userService=userService;
    }
}
