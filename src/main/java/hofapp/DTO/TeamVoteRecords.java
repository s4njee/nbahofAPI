package hofapp.DTO;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TeamVoteRecords {
    private Map<String, TeamVoteRecord> values = new HashMap<>();

    public TeamVoteRecords(LiveTeamRecords liveTeamRecords) {
        values = liveTeamRecords.getValues().entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getValue().getPythagTotalWins() != null ? -1 * entry.getValue().getPythagTotalWins() : 0))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> new TeamVoteRecord(entry.getValue()), (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Map<String, TeamVoteRecord> getValues() {
        return values;
    }

    public void setValues(Map<String, TeamVoteRecord> values) {
        this.values = values;
    }
}
