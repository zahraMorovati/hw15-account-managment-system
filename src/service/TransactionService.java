package service;

import dao.DaoTransaction;
import model.Transaction;

import java.util.List;

public class TransactionService {
    DaoTransaction daoTransaction = new DaoTransaction();

    public List<Transaction> getUserTransactionList(int userId){
        List<Transaction> userTransactions = daoTransaction.getUserTransactions(userId);
        if(userTransactions.isEmpty())
            throw new RuntimeException("there is no transaction!");
        else return userTransactions;

    }

    public List<Transaction> getAccountTransactionList(int accountId){
        List<Transaction> accountTransactions = daoTransaction.getAccountTransactions(accountId);
        if(accountTransactions.isEmpty())
            throw new RuntimeException("there is no transaction!");
        else return accountTransactions;

    }
}
