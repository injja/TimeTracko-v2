package pl.api.timetracko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.api.timetracko.config.securityModels.CustomUserDetails;
import pl.api.timetracko.config.securityServices.CustomUserDetailsService;
import pl.api.timetracko.models.User;
import pl.api.timetracko.repositories.UserRepository;
@Service
public class UserService extends CrudService<User> {
    private final UserRepository userRepository;
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public UserService(UserRepository userRepository, CustomUserDetailsService customUserDetailsService){
        super(userRepository);
        this.userRepository=userRepository;
        this.customUserDetailsService=customUserDetailsService;
    }

    public boolean isAdmin(){
        User user = customUserDetailsService.getCurrentUser().getUser();
        return user.isAdmin();
    }




}
