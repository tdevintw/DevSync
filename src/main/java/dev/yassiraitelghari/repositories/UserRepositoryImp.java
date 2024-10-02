package dev.yassiraitelghari.repositories;

import dev.yassiraitelghari.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class UserRepositoryImp implements UserRepository {

    @Override
    public User add(User user) {
        Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.addAnnotatedClass(User.class).buildSessionFactory();
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        session.close();
        sf.close();
        return user;
    }

    @Override
    public User get(String email) {
        Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.addAnnotatedClass(User.class).buildSessionFactory();
        Session session = sf.getCurrentSession();
        User user = null;
        try{
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            user = query.uniqueResult();
            session.getTransaction().commit();
        }catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace(); // Log the exception
        } finally {
            session.close();
            sf.close();
        }
        return user;
    }

    @Override
    public User update(User user) {
        Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.addAnnotatedClass(User.class).buildSessionFactory();
        Session session = sf.getCurrentSession();
        try {
            session.beginTransaction();
            User getUser = session.get(User.class, user.getId());
            if (getUser != null) {
                getUser.setName(user.getName());
                getUser.setLastName(user.getLastName());
                getUser.setPassword(user.getPassword());
            }
            session.getTransaction().commit();
            return getUser;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
            sf.close();
        }
    }

    @Override
    public boolean delete(User user) {
        Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.addAnnotatedClass(User.class).buildSessionFactory();
        Session session = sf.getCurrentSession();
        try {
            session.beginTransaction();
            User getUser = session.get(User.class, user.getId());
            if (getUser != null) {
                session.delete(getUser);
                session.getTransaction().commit();
                return true;
            } else {
                session.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
            sf.close();
        }
    }
}

