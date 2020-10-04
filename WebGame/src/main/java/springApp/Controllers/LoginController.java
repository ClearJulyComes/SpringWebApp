package springApp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("/login")
    public String getLoginView(){
        return "login";
    }
    @GetMapping
    public String getLevelsView(){
        return "LevelsView";
    }
}
