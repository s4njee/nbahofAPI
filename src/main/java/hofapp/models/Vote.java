package hofapp.models;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "answer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Team team;

    public Answer getAnswer() {
        return answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
