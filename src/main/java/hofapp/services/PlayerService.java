package hofapp.services;

import hofapp.DTO.PlayerWithScore;
import hofapp.models.Player;
import hofapp.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<PlayerWithScore> getAllPlayersWithScores() {
        Iterable<Player> players  = playerRepository.findAll();
        ArrayList<PlayerWithScore> playersWithScores = new ArrayList<>();
        for(Player player : players) {
            PlayerWithScore playerWithScore = new PlayerWithScore(player);
            playerWithScore.setScore(2);
            playersWithScores.add(playerWithScore);
        }

        return playersWithScores;
    }
}


