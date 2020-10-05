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

import java.util.Arrays;
import java.util.List;
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
        int[] lvl = IntStream.range(1, userRepository.findByUsername(user.getUsername()).getLvl()+1).toArray();
        log.warn("Array "+ userRepository.findByUsername(user.getUsername()).getLvl());
        model.addAttribute("levels", lvl);
        return "LevelsView";
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
        if (getObj.isStatus()){
            UserLogin userLogin = userRepository.findByUsername(user.getUsername());
            userLogin.setLvl(userLogin.getLvl()+1);
            userRepository.save(userLogin);
            return "redirect:/levels/start/"+(id+1);
        }
        return "error";
    }

    @PostMapping("/save")
    public String saveLevel(@ModelAttribute("newLevel") LevelData levelData) {
        log.info("Look: " + levelData);
        levelRepository.save(levelData);
        return "redirect:/levels";
    }
}
