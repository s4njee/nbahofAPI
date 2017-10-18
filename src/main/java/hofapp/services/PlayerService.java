package hofapp.services;

import hofapp.DTO.LiveTeamRecordsWithVotes;
import hofapp.DTO.PlayerWithScore;
import hofapp.models.Player;
import hofapp.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<PlayerWithScore> getAllPlayersWithScores(LiveTeamRecordsWithVotes liveTeamRecordsWithVotes) {
        Iterable<Player> players  = playerRepository.findAll();
        return scorePlayers(players, liveTeamRecordsWithVotes);
    }

    public List<PlayerWithScore> getSortedPlayersWithScores(LiveTeamRecordsWithVotes liveTeamRecordsWithVotes) {
        List<PlayerWithScore> playerWithScores = getAllPlayersWithScores(liveTeamRecordsWithVotes);
        return playerWithScores.stream().sorted(Comparator.comparingInt(PlayerWithScore::getScore)).collect(Collectors.toList());
    }

    private List<PlayerWithScore> scorePlayers(Iterable<Player> players, LiveTeamRecordsWithVotes liveTeamRecordsWithVotes) {
        return StreamSupport.stream(players.spliterator(), false)
                .map(player -> scorePlayer(liveTeamRecordsWithVotes, player))
                .collect(Collectors.toList());
    }

    private PlayerWithScore scorePlayer(LiveTeamRecordsWithVotes liveTeamRecordsWithVotes, Player player) {
        int score = liveTeamRecordsWithVotes.getValues().entrySet().stream()
                .mapToInt(team -> team.getValue().isVoteCorrectForPlayer(player.getId()) ?
                        team.getValue().getVotes().get(player.getId()).getCorrectValue():
                        team.getValue().getVotes().get(player.getId()).getIncorrectValue())
                .sum();

        return new PlayerWithScore(player, score);
    }
}


