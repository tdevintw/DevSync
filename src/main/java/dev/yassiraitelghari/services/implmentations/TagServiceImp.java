package dev.yassiraitelghari.services.implmentations;

import dev.yassiraitelghari.domain.Tag;
import dev.yassiraitelghari.domain.Task;
import dev.yassiraitelghari.repositories.interfaces.TagRepository;
import dev.yassiraitelghari.repositories.implmentations.TagRepositoryImp;
import dev.yassiraitelghari.services.interfaces.TagService;

public class TagServiceImp implements TagService {
    private TagRepository tagRepository = new TagRepositoryImp();
    public  boolean add(String[] tags , Task task){
        for(String tag : tags){
            Tag newTag = new Tag();
            newTag.setName(tag);
            newTag.setTask(task);
            tagRepository.add(newTag);
        }
        return true;
    }

    public boolean deleteByTask(int task_id){
        return tagRepository.deleteByTask(task_id);
    }
}
