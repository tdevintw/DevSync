package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.Task;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

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

    Map<Integer , Integer> staticsByWeeks();

    Map<YearMonth, Integer> staticsByMonths();

    Map<Integer , Integer> staticsByYears();
}
