package dev.yassiraitelghari.repositories;

import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRepositoryImp implements UserRepository {

    @Override
    public User add(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String q = "insert into User (username , email ,  name, password , role) values (:username ,:email ,:name, :password , :role)";
            int createdEntities = session.createMutationQuery(q)
                    .setParameter("username", user.getName())
                    .setParameter("email", user.getEmail())
                    .setParameter("name", user.getName())
                    .setParameter("password", user.getPassword())
                    .setParameter("role", user.getRole().toString())
                    .executeUpdate();
            transaction.commit();

        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }else{
                e.printStackTrace(); // This will print to the server logs
                throw new RuntimeException("Failed to add user: " + e.getMessage(), e);
            }
        }
        return user;
    }
}
