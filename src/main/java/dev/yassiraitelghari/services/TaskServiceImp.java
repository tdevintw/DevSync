package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.Task;
import dev.yassiraitelghari.repositories.TaskRepository;
import dev.yassiraitelghari.repositories.TaskRepositoryImp;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.time.temporal.WeekFields;

public class TaskServiceImp implements TaskService {

    private TaskRepository taskRepository = new TaskRepositoryImp();

    public boolean validateTitle(String title) {
        return title.length() >= 3;
    }

    public boolean validateDescription(String description) {
        return description.length() >= 15;
    }

    public boolean validateStartDate(LocalDateTime startDate) {
        return startDate.isAfter(LocalDateTime.now().plusMinutes(1));
    }

    public boolean validateDateLimit(LocalDateTime dateLimit, LocalDateTime startDate) {
        return dateLimit.isAfter(startDate.plusMinutes(2));
    }

    public Task add(Task task) {
        return taskRepository.add(task);
    }

    public boolean isDateWithin3Days(LocalDateTime dateTime) {
        LocalDateTime maxRange = LocalDateTime.now().plusDays(3);
        return !dateTime.isAfter(maxRange);
    }

    public List<Task> findTasks(int id) {
        return taskRepository.findTasks(id);
    }

    public Task update(Task task) {
        return taskRepository.update(task);
    }

    public Task findTask(int taskId) {
        return taskRepository.findTask(taskId);
    }

    public boolean delete(int id) {
        return taskRepository.delete(id);
    }

    public boolean updateTasks(int user_id) {
        return taskRepository.updateTasks(user_id);
    }

    public List<Task> getAll() {
        return taskRepository.getAll();
    }

    public boolean isTaskWithTag(Task task, String tag) {
        return !task.getTags().stream().filter(tag1 -> tag1.getName().equals(tag)).toList().isEmpty();
    }


    public Map<Integer , Integer> staticsByWeeks(){
        Map<Integer , Integer> map = new HashMap<>();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        List<Task> validatedTasks = this.getAll().stream().filter(task -> task.getStatus().equals("Validated")).toList();
        for(Task task : validatedTasks){
            int week = task.getValidatedAt().get(weekFields.weekOfWeekBasedYear());
            if(map.containsKey(week)){
                map.put(week , map.get(week)+1);
            }else{
                map.put(week , 1);
            }
        }
        return map;
    }

    public Map<YearMonth, Integer> staticsByMonths(){
        Map<YearMonth , Integer> map = new HashMap<>();
        List<Task> validatedTasks = this.getAll().stream().filter(task -> task.getStatus().equals("Validated")).toList();
        for(Task task : validatedTasks){
            YearMonth month = YearMonth.from(task.getValidatedAt());
            if(map.containsKey(month)){
                map.put(month , map.get(month)+1);

            }else{
                map.put(month , 1);

            }
        }
        return map;
    }

    public Map<Integer , Integer> staticsByYears(){
        Map<Integer , Integer> map = new HashMap<>();
        List<Task> validatedTasks = this.getAll().stream().filter(task -> task.getStatus().equals("Validated")).toList();
        for(Task task : validatedTasks){
            int year = task.getValidatedAt().getYear();
            if(map.containsKey(year)){
                map.put(year , map.get(year)+1);
            }else{
                map.put(year , 1);
            }
        }
        return map;
    }
}
