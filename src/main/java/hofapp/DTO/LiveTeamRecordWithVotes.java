package hofapp.DTO;

import hofapp.models.Answer;
import hofapp.models.LiveTeamRecord;
import hofapp.models.Vote;

import java.util.*;

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

    private List<String> getCorrectVoteAnswers() {
        if(liveTeamRecord.getPythagTotalWins() == null) {
            return new ArrayList<>();
        }

        if(liveTeamRecord.getPythagTotalWins() > liveTeamRecord.getOverUnder()) {
            return new ArrayList<>(Arrays.asList("OVER", "OVER LOCK"));
        }

        return new ArrayList<>(Arrays.asList("UNDER", "UNDER LOCK"));
    }

    public boolean isVoteCorrectForPlayer(Integer playerId) {
        return getCorrectVoteAnswers().contains(votes.get(playerId).getVoteName());
    }

    public boolean isOver() {
        return getCorrectVoteAnswers().contains("OVER");
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