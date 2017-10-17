package hofapp.DTO;

import hofapp.models.LiveTeamRecord;

import java.util.Map;

public class LiveTeamRecords {

    public Map<String, LiveTeamRecord> getValues() {
        return values;
    }

    public void setValues(Map<String, LiveTeamRecord> values) {
        this.values = values;
    }

    Map<String, LiveTeamRecord> values;

}
