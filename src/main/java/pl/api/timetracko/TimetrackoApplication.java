package pl.api.timetracko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.api.timetracko.models.User;
import pl.api.timetracko.repositories.UserRepository;
import pl.api.timetracko.services.CrudService;
import pl.api.timetracko.services.UserService;

@SpringBootApplication
public class TimetrackoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimetrackoApplication.class, args);
	}


}
