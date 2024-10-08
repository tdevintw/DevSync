package dev.yassiraitelghari.repositories;

import dev.yassiraitelghari.domain.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class TagRepositoryImp implements TagRepository {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PUnit");
    public Tag add(Tag tag){
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(tag);
            entityManager.getTransaction().commit();
            entityManager.close();
            return tag;
    }

    public boolean deleteByTask(int taskId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("DELETE FROM Tag tag WHERE tag.task.id =:id");
            query.setParameter("id" , taskId);
            int rowsAffected = query.executeUpdate();
            entityManager.getTransaction().commit();
            return rowsAffected>0;
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return true;
    }
}
