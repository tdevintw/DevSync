package dev.yassiraitelghari.repositories;

import dev.yassiraitelghari.domain.User;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class UserRepositoryImp implements UserRepository {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PUnit");

    @Override
    public User add(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public User get(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user = null;
        try {
            entityManager.getTransaction().begin();
            TypedQuery<User> query = entityManager.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            user = query.getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace(); // Log the exception
        } finally {
            entityManager.close();
        }
        return user;
    }


    @Override
    public User update(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User updatedUser = null;
        try {
            entityManager.getTransaction().begin();
            updatedUser = entityManager.find(User.class, user.getId());
            if (updatedUser != null) {
                updatedUser.setName(user.getName());
                updatedUser.setLastName(user.getLastName());
                updatedUser.setPassword(user.getPassword());
                entityManager.merge(updatedUser);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return updatedUser;
    }

    @Override
    public boolean delete(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            User getUser = entityManager.find(User.class, user.getId());
            if (getUser != null) {
                entityManager.remove(getUser);
                entityManager.getTransaction().commit();
                return true;
            } else {
                entityManager.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

}

