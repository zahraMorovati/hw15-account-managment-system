package dao;

import model.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static dao.MySessionFactory.sessionFactory;

public class DaoTransaction  {

    public void save(Transaction transaction){
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction beginTransaction = session.beginTransaction();
        session.save(transaction);
        beginTransaction.commit();
        session.close();
    }

    public List<Transaction> getUserTransactions(int userId){
        List<Transaction> results;
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();
        Query<Transaction> query = session.createQuery("from Transaction t where t.user.id=:userId");
        query.setParameter("userId",userId);
        results = query.getResultList();
        transaction.commit();
        session.close();
        return results;
    }

    public List<Transaction> getAccountTransactions(int accountId){
        List<Transaction> results;
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();
        Query<Transaction> query = session.createQuery("from Transaction t where t.account.id=:accountId");
        query.setParameter("accountId",accountId);
        results = query.getResultList();
        transaction.commit();
        session.close();
        return results;
    }
}
