package hofapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String voteName;
    private String displayName;
    private int correctValue;
    private int incorrectValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoteName() {
        return voteName;
    }

    public void setVoteName(String voteName) {
        this.voteName = voteName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getCorrectValue() {
        return correctValue;
    }

    public void setCorrectValue(int correctValue) {
        this.correctValue = correctValue;
    }

    public int getIncorrectValue() {
        return incorrectValue;
    }

    public void setIncorrectValue(int incorrectValue) {
        this.incorrectValue = incorrectValue;
    }
}
