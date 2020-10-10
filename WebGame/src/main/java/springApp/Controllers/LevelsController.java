package springApp.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springApp.Models.GetObj;
import springApp.Models.LevelData;
import springApp.Models.UserLogin;
import springApp.Services.LevelService;
import springApp.Services.UserService;

@Slf4j
@Controller
@RequestMapping("/levels")
public class LevelsController {

    @Autowired
    private LevelService levelService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String getLevelsView(Model model, @AuthenticationPrincipal UserLogin user){
        model.addAttribute("levels", userService.getUserLevels(user.getUsername()));
        return "LevelsView";
    }

    @GetMapping("/rating")
    public String getRatingView(Model model){
        model.addAttribute("users", userService.getTop10());
        return "RatingView";
    }

    @GetMapping("/start/{lvlId}")
    public String getLevelsView(@PathVariable(value="lvlId") final Integer id, Model model,
                                @AuthenticationPrincipal UserLogin user){
        if(userService.checkLevelAccess(user.getUsername(), id)) {
            LevelData levelData = levelService.getLevelData(id);
            model.addAttribute("levels", levelData);
            model.addAttribute("getObj", new GetObj());
            return "SingleLevelView";
        }
        return "error";
    }

    @PostMapping("/done/{lvlId}")
    public String setLevelDone(@ModelAttribute("getObj") GetObj getObj, @PathVariable(value="lvlId") final
            Integer id, @AuthenticationPrincipal UserLogin user){
        if (getObj.isStatus()){
            userService.setFinishedLevel(user.getUsername(), id);
            return "redirect:/levels/start/" + (id + 1);
        }
        return "error";
    }
}
