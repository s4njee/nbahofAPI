package hofapp.DTO;

import hofapp.models.Answer;
import hofapp.models.LiveTeamRecord;
import hofapp.models.Vote;

import java.util.*;

public class TeamVoteRecord {
    private Map<Integer, Answer> votes;

    private LiveTeamRecord liveTeamRecord;

    TeamVoteRecord(LiveTeamRecord liveTeamRecord) {
        this.liveTeamRecord = liveTeamRecord;
    }

    public LiveTeamRecord getLiveTeamRecord() {
        return liveTeamRecord;
    }

    public void setLiveTeamRecord(LiveTeamRecord liveTeamRecord) {
        this.liveTeamRecord = liveTeamRecord;
    }

    private Optional<List<String>> getCorrectVoteAnswers() {
        if(liveTeamRecord.getPythagTotalWins() == null) {
            return Optional.of(new ArrayList<>());
        }

        if(liveTeamRecord.getPythagTotalWins() == liveTeamRecord.getOverUnder())
        {
            return Optional.empty();
        }

        if(liveTeamRecord.getPythagTotalWins() > liveTeamRecord.getOverUnder()) {
            return Optional.of(new ArrayList<>(Arrays.asList("OVER", "OVER LOCK")));
        }

        return Optional.of(new ArrayList<>(Arrays.asList("UNDER", "UNDER LOCK")));
    }

    private Optional<Boolean> isVoteCorrectForPlayer(Integer playerId) {
        Optional<List<String>> correctVoteAnswers = getCorrectVoteAnswers();
        System.out.println(correctVoteAnswers);
        System.out.println(votes);
        System.out.println(playerId);
        return correctVoteAnswers.map(strings -> strings.contains(votes.get(playerId).getVoteName()));
    }

    public String getCorrectnessColour(Integer playerId) {
        return isVoteCorrectForPlayer(playerId)
                .map(isCorrect -> isCorrect ? "green" : "red").orElse("yellow");
    }

    public int getPlayerScore(Integer playerId) {
        Optional<Boolean> playerIsCorrect = isVoteCorrectForPlayer(playerId);

        return playerIsCorrect.map(isCorrect ->
                isCorrect ?
                        votes.get(playerId).getCorrectValue() :
                        votes.get(playerId).getIncorrectValue())
                .orElse(0);
    }

    public boolean isOver() {
        return getCorrectVoteAnswers().map( answers -> answers.contains("OVER")).orElse(false);
    }

    public boolean isUnder() {
        return getCorrectVoteAnswers().map( answers -> answers.contains("UNDER")).orElse(false);
    }

    public boolean isPush() {
        return !isOver() && !isUnder();
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