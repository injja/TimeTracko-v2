package pl.api.timetracko.services;

import org.springframework.stereotype.Service;
import pl.api.timetracko.models.WorkPeriod;
import pl.api.timetracko.repositories.WorkPeriodRepository;
import pl.api.timetracko.requests.WorkspaceRequest;
@Service
public class WorkPeriodService extends CrudService<WorkPeriod> {
    protected WorkPeriodRepository workPeriodRepository;
    public WorkPeriodService(WorkPeriodRepository workPeriodRepository){
        super(workPeriodRepository);
        this.workPeriodRepository=workPeriodRepository;
    }

    public WorkPeriod create(){
        WorkPeriod newWorkPeriod=new WorkPeriod();
        //newWorkPeriod.setWorkday();
        //newWorkPeriod.setStart();
        return workPeriodRepository.save(newWorkPeriod);
    }
}
