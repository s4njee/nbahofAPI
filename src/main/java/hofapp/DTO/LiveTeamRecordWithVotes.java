package hofapp.DTO;

import hofapp.models.Answer;
import hofapp.models.LiveTeamRecord;
import hofapp.models.Vote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LiveTeamRecordWithVotes  {
    private Map<Integer, Answer> votes;

    private LiveTeamRecord liveTeamRecord;

    LiveTeamRecordWithVotes(LiveTeamRecord liveTeamRecord) {
        this.liveTeamRecord = liveTeamRecord;
    }

    public LiveTeamRecord getLiveTeamRecord() {
        return liveTeamRecord;
    }

    public void setLiveTeamRecord(LiveTeamRecord liveTeamRecord) {
        this.liveTeamRecord = liveTeamRecord;
    }

    public Map<Integer, Answer> getVotes() {
        return votes;
    }

    public void addVote(Vote vote) {
        if(votes == null) {
            votes = new HashMap<>();
        }
        votes.put(vote.getPlayer().getId(), vote.getAnswer());
    }
}