package dev.yassiraitelghari.services;

import java.time.LocalDateTime;

public class TaskServiceImp implements TaskService{

    public boolean validateTitle(String title){
        return title.length() >= 3;
    }

    public boolean validateDescription(String description){
        return description.length() >=15 ;
    }

    public boolean validateStartDate(LocalDateTime startDate){
        return startDate.isAfter(LocalDateTime.now().plusMinutes(1));
    }

    public boolean validateDateLimit(LocalDateTime dateLimit){
        return dateLimit.isAfter(LocalDateTime.now().plusMinutes(2));
    }

}
