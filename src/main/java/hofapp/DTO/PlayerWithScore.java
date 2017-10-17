package hofapp.DTO;

import hofapp.models.Player;

public class PlayerWithScore extends Player {

    public PlayerWithScore(Player player) {
        this.setId(player.getId());
        this.setFirstName(player.getFirstName());
    }

    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
