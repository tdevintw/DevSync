package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.Task;

public interface TagService{

    boolean add(String[] tags , Task task);

    boolean deleteByTask(int task_id);
}
