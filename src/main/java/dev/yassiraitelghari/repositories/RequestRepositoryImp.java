package dev.yassiraitelghari.repositories;

import dev.yassiraitelghari.domain.Request;
import dev.yassiraitelghari.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import javax.management.Query;
import java.util.Collections;
import java.util.List;

public class RequestRepositoryImp implements RequestRepository {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PUnit");

    public Request add(Request request) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(request);
        entityManager.getTransaction().commit();
        return request;
    }

    public List<Request> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Request> requests = null;
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Request> query = entityManager.createQuery("FROM Request WHERE status = :status", Request.class);
            query.setParameter("status", "Pending");
            requests = query.getResultList();
            entityManager.getTransaction().commit();
            return requests;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return requests;
    }

    @Override
    public Request get(int requestId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Request request = null;
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Request> query = entityManager.createQuery("FROM Request WHERE id = :id", Request.class);
            query.setParameter("id", requestId);
            request = query.getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace(); // Log the exception
        } finally {
            entityManager.close();
        }
        return request;
    }

    @Override
    public Request update(Request request) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Request updatedRequest = null;
        try {
            updatedRequest = entityManager.find(Request.class, request.getId());
            if (updatedRequest != null) {
                entityManager.getTransaction().begin();
                updatedRequest.setTask(request.getTask());
                updatedRequest.setId(request.getId());
                updatedRequest.setMessage(request.getMessage());
                updatedRequest.setCreatedAt(request.getCreatedAt());
                updatedRequest.setStatus(request.getStatus());
                entityManager.merge(updatedRequest);
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return updatedRequest;
    }

    @Override
    public List<Request> pendingRequests() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Request> query = entityManager.createQuery("SELECT request FROM Request  request WHERE request.status =:status", Request.class);
            query.setParameter("status", "Pending");
            List<Request> requests = query.getResultList();
            entityManager.getTransaction().commit();
            return requests;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            entityManager.close();
        }
    }
}
