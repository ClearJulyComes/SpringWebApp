package springApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springApp.Services.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public RegistrationController(
            UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        userService.registerNewUser(form.toUserLogin(passwordEncoder));
        return "redirect:/login";
    }
}
