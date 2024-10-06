package dev.yassiraitelghari.services;

import dev.yassiraitelghari.domain.Tag;
import dev.yassiraitelghari.domain.Task;
import dev.yassiraitelghari.repositories.TagRepository;
import dev.yassiraitelghari.repositories.TagRepositoryImp;

public class TagServiceImp implements TagService{
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
}
