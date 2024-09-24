package pl.api.timetracko.repositories;

import org.springframework.stereotype.Repository;
import pl.api.timetracko.models.WorkPeriod;
@Repository
public interface WorkPeriodRepository extends CrudRepository<WorkPeriod> {
}
