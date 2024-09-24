package pl.api.timetracko.repositories;

import org.springframework.stereotype.Repository;
import pl.api.timetracko.models.Workday;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface WorkDayRepository extends CrudRepository<Workday> {
//    Optional<Workday> findByUserIdAndDate(Long user, LocalDate date);
}
