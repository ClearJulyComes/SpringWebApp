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
import springApp.Repositories.LevelRepository;
import springApp.Repositories.UserRepository;

import java.util.stream.IntStream;

@Slf4j
@Controller
@RequestMapping("/levels")
public class LevelsController {

    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getLevelsView(Model model, @AuthenticationPrincipal UserLogin user){
        log.warn("User authorities "+user.getAuthorities().toString());
        int[] lvl = IntStream.range(1, userRepository.findByUsername(user.getUsername()).getLvl()+1).toArray();
        model.addAttribute("levels", lvl);
        return "LevelsView";
    }

    @GetMapping("/rating")
    public String getRatingView(Model model, @AuthenticationPrincipal UserLogin user){
        model.addAttribute("users", userRepository.findTop10ByOrderByLvlDesc());
        return "RatingView";
    }

    @GetMapping("/start/{lvlId}")
    public String getLevelsView(@PathVariable(value="lvlId") final Integer id, Model model,
                                @AuthenticationPrincipal UserLogin user){
        log.info(levelRepository.findById(id) + " Ty");
        if(userRepository.findByUsername(user.getUsername()).getLvl()>=id) {
            LevelData levelData = levelRepository.findById(id).orElse(new LevelData());
            model.addAttribute("levels", levelData);
            model.addAttribute("getObj", new GetObj());
            return "SingleLevelView";
        }
        return "error";
    }

    @PostMapping("/done/{lvlId}")
    public String setLevelDone(@ModelAttribute("getObj") GetObj getObj, @PathVariable(value="lvlId") final
            Integer id, Model model, @AuthenticationPrincipal UserLogin user){
        UserLogin userLogin = userRepository.findByUsername(user.getUsername());
        if (getObj.isStatus()){
            log.warn("Log " + id + " and " + userLogin.getLvl());
            if(id==userLogin.getLvl()){
                log.warn("Log equal " + id + " and " + userLogin.getLvl());
                userLogin.setLvl(userLogin.getLvl() + 1);
                userRepository.save(userLogin);
            }
            return "redirect:/levels/start/" + (id + 1);
        }
        return "error";
    }
}
