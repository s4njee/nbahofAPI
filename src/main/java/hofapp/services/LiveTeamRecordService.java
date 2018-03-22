package hofapp.services;

import hofapp.DTO.LiveTeamRecords;
import hofapp.DTO.TeamVoteRecords;
import hofapp.models.Vote;
import hofapp.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LiveTeamRecordService {

    @Autowired
    private VoteRepository voteRepository;

    public TeamVoteRecords getAllLiveTeamRecords() {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("Here 1");
        LiveTeamRecords liveTeamRecords = restTemplate.getForObject("http://mattandsam.herokuapp.com/scrape", LiveTeamRecords.class);
        System.out.println(liveTeamRecords);
        System.out.println("liveTeamRecords");
        TeamVoteRecords decorated = decorateLiveTeamRecordsWithVotes(liveTeamRecords);
        System.out.println(decorated);
        return decorated;
    }

    private TeamVoteRecords decorateLiveTeamRecordsWithVotes(LiveTeamRecords liveTeamRecords) {
        TeamVoteRecords teamVoteRecords = new TeamVoteRecords(liveTeamRecords);
        Iterable<Vote> votes = voteRepository.findAll();

        System.out.println("Adding votes");
        votes.iterator().forEachRemaining(vote ->
                teamVoteRecords.getValues().get(vote.getTeam().getTeamName()).addVote(vote));

        System.out.println("added votes");
        return teamVoteRecords;
    }
}
