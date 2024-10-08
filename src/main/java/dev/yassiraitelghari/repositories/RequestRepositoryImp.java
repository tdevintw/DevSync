package dev.yassiraitelghari.repositories;

import dev.yassiraitelghari.domain.Request;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RequestRepositoryImp implements RequestRepository{
private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PUnit");
    public Request add(Request request){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(request);
        entityManager.getTransaction().commit();
        return request;
    }

}
