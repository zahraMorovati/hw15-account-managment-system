package dao;

import model.Account;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

import static dao.MySessionFactory.sessionFactory;

public class DaoAccount {

    public List<Account> findById(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Account> query = session.createQuery("from Account a where a.id=:id");
        query.setParameter("id",id);
        List<Account> results = query.getResultList();
        transaction.commit();
        session.close();
        return results;
    }

    public List<Account> findAll(int userId){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Account> query = session.createQuery("from Account a where a.user.id=:userId");
        query.setParameter("userId",userId);
        List<Account> results = query.getResultList();
        transaction.commit();
        session.close();
        return results;
    }

    public void deposit(Account account,double amount){
        double newBalance = account.getBalance()+amount;
        int result = getResult(account, newBalance);
        if(result<0)
            throw new RuntimeException("operation deposit failed!");
    }

    private int getResult(Account account, double newBalance) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("update Account a set a.balance=:newBalance,a.modifyDate=:newDate where a.id=:id");
        query.setParameter("id", account.getId());
        query.setParameter("newBalance", newBalance);
        query.setParameter("newDate", new Date());
        int result = query.executeUpdate();
        transaction.commit();
        session.close();
        return result;
    }

    public void withraw(Account account,double amount){
        if(account.getBalance()>amount) {
            double newBalance = account.getBalance()-amount;
            int result = getResult(account, newBalance);
            if(result<0)
                throw new RuntimeException("operation withdraw failed!");
        }else {
            throw new RuntimeException("balance is not enough!");
        }
    }





}
