package hofapp.DTO;

import hofapp.models.LiveTeamRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LiveTeamRecordsWithVotes {
    private Map<String, LiveTeamRecordWithVotes> values = new HashMap<>();

    public LiveTeamRecordsWithVotes(LiveTeamRecords liveTeamRecords) {
        values = liveTeamRecords.getValues().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> new LiveTeamRecordWithVotes(entry.getValue())));
    }

    public Map<String, LiveTeamRecordWithVotes> getValues() {
        return values;
    }

    public void setValues(Map<String, LiveTeamRecordWithVotes> values) {
        this.values = values;
    }
}
