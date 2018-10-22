package hofapp.services;

import hofapp.DTO.LiveTeamRecords;
import hofapp.DTO.TeamVoteRecords;
import hofapp.models.LiveTeamRecord;
import hofapp.models.Vote;
import hofapp.repositories.LiveTeamRecordRepository;
import hofapp.repositories.VoteRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.StreamSupport;

@Service
public class LiveTeamRecordService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private LiveTeamRecordRepository liveTeamRecordRepository;

    public TeamVoteRecords getAllLiveTeamRecords() {

        Calendar calendar = new GregorianCalendar();

        return getAllLiveTeamRecords(
                Integer.toString(calendar.get(Calendar.YEAR)),
                Integer.toString(calendar.get(Calendar.MONTH)+1),
                Integer.toString(calendar.get(Calendar.DATE))
        );
    }

    public TeamVoteRecords getAllLiveTeamRecords(String year, String month, String day) {

        if(StringUtils.isBlank(year) || StringUtils.isBlank(month) || StringUtils.isBlank(day)) {
            return getAllLiveTeamRecords();
        }

        Iterable<LiveTeamRecord> allLiveTeamRecords = liveTeamRecordRepository.findAll();

        RestTemplate restTemplate = new RestTemplate();
        LiveTeamRecords liveTeamRecords = restTemplate.getForObject(
                "http://mattandsam.herokuapp.com/scrape/year/" + year + "/month/" + month + "/day/" + day,
                LiveTeamRecords.class);

        //Method so good we call it twice...
        if(liveTeamRecords.getValues() == null){
            liveTeamRecords = restTemplate.getForObject(
                    "http://mattandsam.herokuapp.com/scrape/year/" + year + "/month/" + month + "/day/" + day,
                    LiveTeamRecords.class);
        }


        return decorateLiveTeamRecordsWithVotes(liveTeamRecords);
    }

    private TeamVoteRecords decorateLiveTeamRecordsWithVotes(LiveTeamRecords liveTeamRecords) {
        TeamVoteRecords teamVoteRecords = new TeamVoteRecords(liveTeamRecords);
        Iterable<Vote> votes = voteRepository.findAll();
        teamVoteRecords.setVotes(votes);
        return teamVoteRecords;
    }
}
