package dao;

import model.Account;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static dao.MySessionFactory.sessionFactory;

public class DaoUser{

    public void save(User user){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void addAccount(int userId, Account account){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user1 = session.get(User.class,userId);
        if(user1!=null){
            user1.getAccountList().add(account);
            session.update(user1);
        }else {
            throw new RuntimeException("cannot find user!");
        }
        transaction.commit();
        session.close();
    }

    public List<User> findById(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User u where u.id=:id");
        query.setParameter("id",id);
        List<User> results = query.getResultList();
        throwNewExceptionForResults(results, "user not found!");
        transaction.commit();
        session.close();
        return results;
    }

    public List<User> findAll(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User");
        List<User> results = query.getResultList();
        throwNewExceptionForResults(results, "there is no user!");
        transaction.commit();
        session.close();
        return results;
    }

    public List<User> findByName(String name){
        List<User> results;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User u where u.name=:name");
        query.setParameter("name",name);
        results = query.getResultList();
        throwNewExceptionForResults(results, "user not found!");
        transaction.commit();
        session.close();
        return results;
    }

    public List<User> findByFamilyName(String family){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User u where u.family=:family");
        query.setParameter("family",family);
        List<User> results = query.getResultList();
        throwNewExceptionForResults(results, "user not found!");
        transaction.commit();
        session.close();
        return results;
    }

    public List<User> findByCardNumber(long cardNumber){
        List<User> results;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User u where u.cardNumner=:cardNumner");
        query.setParameter("cardNumner",cardNumber);
        results = query.getResultList();
        throwNewExceptionForResults(results, "user not found!");
        transaction.commit();
        session.close();
        return results;
    }

    private void throwNewExceptionForResults(List<User> results, String s) {
        if (results.isEmpty())
            throw new RuntimeException(s);
    }


}
