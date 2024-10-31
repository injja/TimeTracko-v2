package pl.api.timetracko.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.api.timetracko.config.securityModels.CustomUserDetails;
import pl.api.timetracko.config.securityServices.CustomUserDetailsService;
import pl.api.timetracko.models.User;
import pl.api.timetracko.requests.UserRequest;
import pl.api.timetracko.responses.MessageResponse;
import pl.api.timetracko.services.CrudService;
import pl.api.timetracko.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends CrudController<User> {
    protected UserService userService;
    protected CustomUserDetailsService customUserDetailsService;
    public UserController(UserService userService, CustomUserDetailsService customUserDetailsService) {
        super(userService);
        this.userService=userService;
        this.customUserDetailsService=customUserDetailsService;

    }
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        CustomUserDetails userDetails = customUserDetailsService.getCurrentUser();
        return ResponseEntity.ok().body(userDetails.getUser());
    }
}
