package hofapp.DTO;

import hofapp.models.Player;

public class PlayerWithScore extends Player {

    public PlayerWithScore(Player player, int score) {
        this.setId(player.getId());
        this.setFirstName(player.getFirstName());
        this.score = score;
    }

    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
