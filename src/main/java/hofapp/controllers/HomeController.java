package hofapp.controllers;

import hofapp.DTO.TeamVoteRecords;
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
        TeamVoteRecords teamRecords = liveTeamRecordService.getAllLiveTeamRecords();

        model.addAttribute("players", playerService.getSortedPlayersWithScores(teamRecords));
        model.addAttribute("liveTeamRecords", teamRecords);
        return "home";
    }

    @RequestMapping("/results")
    public String results(Model model) {
        TeamVoteRecords teamRecords = liveTeamRecordService.getAllLiveTeamRecords();
        model.addAttribute("players", playerService.getSortedPlayersWithScores(teamRecords));
        model.addAttribute("liveTeamRecords", teamRecords);
        return "results";
    }
}
