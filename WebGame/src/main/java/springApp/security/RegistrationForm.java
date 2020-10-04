package springApp.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import springApp.Models.UserLogin;

@Data
public class RegistrationForm {
    private String username;
    private String password;

    public UserLogin toUserLogin(PasswordEncoder passwordEncoder) {
        return new UserLogin(username, passwordEncoder.encode(password));
    }
}
