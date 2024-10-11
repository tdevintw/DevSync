package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {
    boolean validateTitle(String title);

    boolean validateDescription(String description);

    boolean validateStartDate(LocalDateTime startDate);

    boolean validateDateLimit(LocalDateTime dateLimit , LocalDateTime startDate);

    boolean isDateWithin3Days(LocalDateTime dateTime);

    Task add(Task tak);

    List<Task> findTasks(int id);

    Task update(Task task);

    Task findTask(int taskId);

    boolean delete(int id);

    boolean updateTasks(int user_id);

    List<Task> getAll();

    boolean isTaskWithTag(Task task , String tag);
}
