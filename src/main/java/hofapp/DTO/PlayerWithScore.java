package hofapp.DTO;

import hofapp.models.Player;

public class PlayerWithScore extends Player {

    private Integer score;
    private Integer certainScore;

    public PlayerWithScore(Player player, int score, int certainScore) {
        this.setId(player.getId());
        this.setFirstName(player.getFirstName());
        this.score = score;
        this.certainScore = certainScore;
    }

    public Integer getCertainScore() {
        return certainScore;
    }

    public void setCertainScore(Integer certainScore) {
        this.certainScore = certainScore;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCombinedScore() {
        return score + " (" + certainScore + ")";
    }
}
