package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.Task;
import dev.yassiraitelghari.repositories.TaskRepository;
import dev.yassiraitelghari.repositories.TaskRepositoryImp;

import java.time.LocalDateTime;

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

}
