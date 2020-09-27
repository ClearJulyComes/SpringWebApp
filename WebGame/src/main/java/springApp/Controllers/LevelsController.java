package springApp.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springApp.Models.LevelData;
import springApp.Repositories.LevelRepository;

@Slf4j
@Controller
@RequestMapping("/levels")
public class LevelsController {

    @Autowired
    private LevelRepository levelRepository;

    @GetMapping
    public String getLevelsView(Model model){
        model.addAttribute("levels", levelRepository.findAll());
        return "LevelsView";
    }

    @PostMapping("/save")
    public String saveLevel(@RequestBody LevelData levelData) {
        levelRepository.save(levelData);
        return "redirect:/";
    }
}
