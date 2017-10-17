package hofapp.controllers;

import hofapp.services.LiveTeamRecordService;
import hofapp.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private LiveTeamRecordService liveTeamRecordService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("players", playerService.getAllPlayersWithScores());
        return "home";
    }

    @RequestMapping("/results")
    public String results(Model model) {
        model.addAttribute("players", playerService.getAllPlayersWithScores());
        model.addAttribute("liveTeamRecords", liveTeamRecordService.getAllLiveTeamRecords());
        return "results";
    }
}
