package pl.api.timetracko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.api.timetracko.models.CustomUserDetails;
import pl.api.timetracko.repositories.UserRepository;
@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        pl.api.timetracko.models.User user = userRepository.findByUsername(username);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        else {
            return new CustomUserDetails(user);
        }
    }
}
