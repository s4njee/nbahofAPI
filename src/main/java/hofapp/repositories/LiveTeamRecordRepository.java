package hofapp.repositories;

import hofapp.models.LiveTeamRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveTeamRecordRepository extends CrudRepository<LiveTeamRecord, Long> {
}
