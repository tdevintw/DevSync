package dev.yassiraitelghari.repositories.interfaces;

import dev.yassiraitelghari.domain.Task;

import java.util.List;

public interface TaskRepository {

    Task add(Task task);

    List<Task> findTasks(int id);

    Task update(Task task);

    Task findTask(int taskId);

    boolean delete(int id);

    boolean updateTasks(int user_id);

    List<Task> getAll();


}
