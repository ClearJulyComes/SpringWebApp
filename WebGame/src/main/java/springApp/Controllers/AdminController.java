package springApp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springApp.Models.LevelData;

@Controller("/admin")
public class AdminController {

    @GetMapping
    public String showAdminView(Model model){
        model.addAttribute("newLevel", new LevelData());
        return "AdminView";
    }
}
