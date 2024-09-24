package pl.api.timetracko.services;

import org.springframework.stereotype.Service;
import pl.api.timetracko.models.Workday;
import pl.api.timetracko.repositories.WorkDayRepository;

@Service
public class WorkdayService extends CrudService<Workday> {
    protected WorkDayRepository workDayRepository;
    public WorkdayService(WorkDayRepository workDayRepository){
        super(workDayRepository);
        this.workDayRepository=workDayRepository;
    }

    public Workday create(){
        Workday newWorkday=new Workday();
        //newWorkday.setUser();
        //newWorkday.setDate();
        return workDayRepository.save(newWorkday);
    }
}
