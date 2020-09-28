package springApp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springApp.Models.LevelData;

@Controller("/")
public class LoginController {

    @GetMapping
    public String index(Model model){
        model.addAttribute("newLevel", new LevelData());
        return "LoginView";
    }
}
