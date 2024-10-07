package dev.yassiraitelghari.repositories;

import dev.yassiraitelghari.domain.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TaskRepositoryImp implements TaskRepository {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PUnit");

    @Override
    public Task add(Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.getTransaction().commit();
        entityManager.close();
        return task;
    }
    @Override

    public List<Task> findTasks(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Task> tasks = null;
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Task> query = entityManager.createQuery("FROM Task WHERE user.id = :id", Task.class);
            query.setParameter("id", id);
            tasks = query.getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace(); // Log the exception
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return tasks;
    }
    @Override

    public Task update(Task task){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Task updatedTask = null;
        try {
            entityManager.getTransaction().begin();
            updatedTask = entityManager.find(Task.class , task.getId());
            if(updatedTask!=null){
                updatedTask.setName(task.getName());
                updatedTask.setUser(task.getUser());
                updatedTask.setStartDate(task.getStartDate());
                updatedTask.setDateLimit(task.getDateLimit());
                updatedTask.setDescription(task.getDescription());
                updatedTask.setStatus(task.getStatus());
                entityManager.merge(updatedTask);
            }
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return updatedTask;
    }

   public Task findTask(int taskId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Task task = null;
        try{
            entityManager.getTransaction().begin();
            TypedQuery<Task> query=entityManager.createQuery("FROM Task WHERE id = :id ",Task.class) ;
            query.setParameter("id",taskId);
            task = query.getSingleResult();
            entityManager.getTransaction().commit();
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return task;
   }
    @Override

    public boolean delete(int id){
         EntityManager entityManager = entityManagerFactory.createEntityManager();
         try {
             entityManager.getTransaction().begin();
             Task task = entityManager.find(Task.class, id);
             if (task != null) {
                 entityManager.remove(task);
                 entityManager.getTransaction().commit();
                 return true;
             } else {
                 entityManager.getTransaction().rollback();
                 return false;
             }

         }catch (Exception e){
             if(entityManager.getTransaction().isActive()){
                 entityManager.getTransaction().rollback();
             }
             e.printStackTrace();
         }
         return true;
   }
}

