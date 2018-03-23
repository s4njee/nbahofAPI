package hofapp.services;

import hofapp.DTO.TeamVoteRecords;
import hofapp.DTO.PlayerWithScore;
import hofapp.models.Player;
import hofapp.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<PlayerWithScore> getSortedPlayersWithScores(TeamVoteRecords teamVoteRecords) {
        List<PlayerWithScore> playerWithScores = getAllPlayersWithScores(teamVoteRecords);
        return playerWithScores.stream().sorted(Comparator.comparingInt(player -> player.getScore()*-1)).collect(Collectors.toList());
    }

    private List<PlayerWithScore> getAllPlayersWithScores(TeamVoteRecords teamVoteRecords) {
        Iterable<Player> players  = playerRepository.findAll();
        return scorePlayers(players, teamVoteRecords);
    }

    private List<PlayerWithScore> scorePlayers(Iterable<Player> players, TeamVoteRecords teamVoteRecords) {
        return StreamSupport.stream(players.spliterator(), false)
                .map(player -> scorePlayer(teamVoteRecords, player))
                .collect(Collectors.toList());
    }

    private PlayerWithScore scorePlayer(TeamVoteRecords teamVoteRecords, Player player) {
        int score = teamVoteRecords.getValues().entrySet().stream()
                .mapToInt(team -> team.getValue().getPlayerScore(player.getId()))
                .sum();

        int certainScore = teamVoteRecords.getValues().entrySet().stream()
                .mapToInt(team -> team.getValue().getPlayerCertainScore(player.getId()))
                .sum();

        return new PlayerWithScore(player, score, certainScore);
    }
}


