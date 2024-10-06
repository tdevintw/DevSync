package dev.yassiraitelghari.repositories;

import dev.yassiraitelghari.domain.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
}
