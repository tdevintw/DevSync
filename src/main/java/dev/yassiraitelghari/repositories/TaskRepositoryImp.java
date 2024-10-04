package dev.yassiraitelghari.repositories;

import dev.yassiraitelghari.domain.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TaskRepositoryImp implements TaskRepository{
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PUnit");
    @Override
    public Task add(Task task){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(task);
            entityManager.getTransaction().commit();
            entityManager.close();
            return task;
        }
    }
}
