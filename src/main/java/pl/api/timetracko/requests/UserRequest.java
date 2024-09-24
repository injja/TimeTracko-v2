package pl.api.timetracko.requests;

import lombok.Data;

@Data
public class UserRequest {
    String username;
    String name;
    String surname;
    String password;
    String email;
}
