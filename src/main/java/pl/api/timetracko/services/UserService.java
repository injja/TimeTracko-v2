package pl.api.timetracko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.api.timetracko.models.User;
import pl.api.timetracko.repositories.UserRepository;
@Service
public class UserService extends CrudService<User> {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        super(userRepository);
        this.userRepository=userRepository;
    }


}
