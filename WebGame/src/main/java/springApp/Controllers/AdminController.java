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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showAdminView(Model model){
        model.addAttribute("newLevel", new LevelData());
        model.addAttribute("user", new GetObj());
        return "AdminView";
    }

    @PostMapping("/add")
    public String addAdmin(@ModelAttribute("getObj") GetObj getObj){
        String user = getObj.getLogin();
        UserLogin userLogin = userRepository.findByUsername(user);
        userLogin.setAdmin(true);
        userRepository.save(userLogin);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteAdmin(@RequestBody String user){
        UserLogin userLogin = userRepository.findByUsername(user);
        userLogin.setAdmin(false);
        userRepository.save(userLogin);
        return "redirect:/admin";
    }

    @GetMapping("/levels")
    public String getAdminLevelsView(Model model, @AuthenticationPrincipal UserLogin user){
        int[] lvl = IntStream.range(1, levelRepository.findFirstByOrderByLvlIdDesc().getLvlId()+1).toArray();
        model.addAttribute("levels", lvl);
        log.warn("Return");
        return "AdminLevelsView";
    }

    @GetMapping("/levels/delete/{lvlId}")
    public String deleteLevel(@PathVariable(value="lvlId") final Integer id, Model model){
        levelRepository.delete(levelRepository.findById(id).orElse(new LevelData()));
        return "redirect:/admin/levels";
    }

    @PostMapping("/levels/save")
    public String saveLevel(@ModelAttribute("newLevel") LevelData levelData) {
        log.info("Look: " + levelData);
        levelRepository.save(levelData);
        return "redirect:/admin/levels";
    }
}
