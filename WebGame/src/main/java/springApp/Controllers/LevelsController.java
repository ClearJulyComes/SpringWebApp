package springApp.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springApp.Models.GetObj;
import springApp.Models.LevelData;
import springApp.Models.UserLevels;
import springApp.Repositories.LevelRepository;
import springApp.Repositories.UserLevelsRepository;

@Slf4j
@Controller
@RequestMapping("/levels")
public class LevelsController {

    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private UserLevelsRepository userLevelsRepository;

    @GetMapping
    public String getLevelsView(Model model){
        model.addAttribute("levels", levelRepository.findAll());
        return "LevelsView";
    }

    @GetMapping("/start/{lvlId}")
    public String getLevelsView(@PathVariable(value="lvlId") final Integer id, Model model){
        log.info(levelRepository.findById(id) + " Ty");
        LevelData levelData = levelRepository.findById(id).orElse(new LevelData());
        model.addAttribute("levels", levelData);
        model.addAttribute("getObj", new GetObj());
        return "SingleLevelView";
    }

    @PostMapping("/done/{lvlId}")
    public String setLevelDone(@ModelAttribute("getObj") GetObj getObj, @PathVariable(value="lvlId") final
            Integer id, Model model){
        if (getObj.isStatus()){
            //UserLevels userLevels = new UserLevels(userLogin, UserLogin, id);
            //userLevelsRepository.save(userLevels);
            return "redirect:/levels/start/"+(id+1);
        }
        return "LevelsView";
    }

    @PostMapping("/save")
    public String saveLevel(@ModelAttribute("newLevel") LevelData levelData) {
        log.info("Look: " + levelData);
        levelRepository.save(levelData);
        return "redirect:/levels";
    }
}
