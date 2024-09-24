package pl.api.timetracko.services;

import pl.api.timetracko.models.Task;
import pl.api.timetracko.repositories.TaskRepository;
import pl.api.timetracko.requests.TaskRequest;

public class TaskService extends CrudService<Task> {
    protected TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository){
        super(taskRepository);
        this.taskRepository=taskRepository;
    }

    public Task create(TaskRequest taskRequest){
        Task newTask=new Task();
        newTask.setName(taskRequest.getName());
       // newTask.setCreated();
       // newTask.setCreatedBy();
        newTask.setDescription(taskRequest.getDescription());
        newTask.setPriority(taskRequest.getPriority());
       // newTask.setDueTo();
       // newTask.setProject();
        return taskRepository.save(newTask);
    }
}
