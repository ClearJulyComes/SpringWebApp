package springApp.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springApp.Models.GetObj;
import springApp.Models.LevelData;
import springApp.Services.LevelService;
import springApp.Services.UserService;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final LevelService levelService;

    @Autowired
    public AdminController(UserService userService, LevelService levelService){
        this.levelService = levelService;
        this.userService = userService;
    }

    @GetMapping
    public String showAdminView(Model model){
        model.addAttribute("newLevel", new LevelData());
        model.addAttribute("user", new GetObj());
        return "AdminView";
    }

    @PostMapping("/add")
    public String addAdmin(@ModelAttribute("getObj") GetObj getObj){
        String user = getObj.getLogin();
        if (!userService.addOrDeleteAdmin(user, true)) {
            log.warn("Error with saving with admin role.");
        }
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteAdmin(@RequestBody String user){
        if (!userService.addOrDeleteAdmin(user, false)) {
            log.warn("Error with deleting with admin role.");
        }
        return "redirect:/admin";
    }

    @GetMapping("/levels")
    public String getAdminLevelsView(Model model){
        model.addAttribute("levels", levelService.getAllLevels());
        return "AdminLevelsView";
    }

    @GetMapping("/levels/delete/{lvlId}")
    public String deleteLevel(@PathVariable(value="lvlId") final Integer id){
        levelService.deleteLevel(id);
        return "redirect:/admin/levels";
    }

    @PostMapping("/levels/save")
    public String saveLevel(@ModelAttribute("newLevel") LevelData levelData) {
        levelService.saveLevel(levelData);
        return "redirect:/admin/levels";
    }
}
