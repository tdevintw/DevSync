package dev.yassiraitelghari.repositories.interfaces;

import dev.yassiraitelghari.domain.Tag;

public interface TagRepository {

    Tag add(Tag tag);

    boolean deleteByTask(int taskId);
}
