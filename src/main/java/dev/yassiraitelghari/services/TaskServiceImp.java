package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.Task;
import dev.yassiraitelghari.repositories.TaskRepository;
import dev.yassiraitelghari.repositories.TaskRepositoryImp;

import java.time.LocalDateTime;
import java.util.List;

public class TaskServiceImp implements TaskService{

    private TaskRepository taskRepository = new TaskRepositoryImp();
    public boolean validateTitle(String title){
        return title.length() >= 3;
    }

    public boolean validateDescription(String description){
        return description.length() >=15 ;
    }

    public boolean validateStartDate(LocalDateTime startDate){
        return startDate.isAfter(LocalDateTime.now().plusMinutes(1));
    }

    public boolean validateDateLimit(LocalDateTime dateLimit , LocalDateTime startDate){
        return dateLimit.isAfter(startDate.plusMinutes(2));
    }

    public Task add(Task task){
        return taskRepository.add(task);
    }

    public boolean isDateWithin3Days(LocalDateTime dateTime){
        LocalDateTime maxRange = LocalDateTime.now().plusDays(3);
        return !dateTime.isAfter(maxRange);
    }

    public List<Task> findTasks(int id){
        return taskRepository.findTasks(id);
    }

    public Task update(Task task){
        return taskRepository.update(task);
    }

    public Task findTask(int taskId){
        return taskRepository.findTask(taskId);
    }

}
