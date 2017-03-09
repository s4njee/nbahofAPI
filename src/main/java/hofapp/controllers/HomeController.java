package hofapp.controllers;

import hofapp.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("players", playerRepository.findAll());
        return "home";
    }
}
