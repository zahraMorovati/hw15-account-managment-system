package service;

import dao.DaoAccount;
import dao.DaoTransaction;
import model.Account;
import model.Transaction;
import model.User;
import model.enumation.TransactionType;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    DaoAccount daoAccount = new DaoAccount();
    UserServiceImpl userService = new UserServiceImpl();
    DaoTransaction daoTransaction = new DaoTransaction();

    @Override
    public void withdraw(int userId, int accountId, double withdrawAmount) {
        Account account = daoAccount.findById(accountId).get(0);
        User user = userService.getUserById(userId);

        if (account != null && user!=null) {
            Transaction transaction = getTransaction(account, user, TransactionType.WITHDRAW);
            daoAccount.withraw(account, withdrawAmount);
            daoTransaction.save(transaction);

        } else throw new RuntimeException("invalid account number or user id!");

    }

    @Override
    public void deposit(int userId, int accountId, double depositAmount) {
        Account account = daoAccount.findById(accountId).get(0);
        User user = userService.getUserById(userId);

        if (account != null && user!=null) {
            Transaction transaction = getTransaction(account, user, TransactionType.DIPOSIT);
            daoAccount.deposit(account, depositAmount);
            daoTransaction.save(transaction);

        } else throw new RuntimeException("invalid account number or user id!");
    }

    private Transaction getTransaction(Account account, User user, TransactionType dwposit) {
        return Transaction.TransactionBuilder.aTransaction()
                .setTransactionType(dwposit)
                .setAccount(account)
                .setUser(user)
                .build();
    }


    @Override
    public List<Account> getAllAccouts(int userId) {
        List<Account> accountList = daoAccount.findAll(userId);
        if (accountList.isEmpty())
            throw new RuntimeException("there is no account for this user!");
        else return accountList;
    }

    @Override
    public Account getAccountById(int id) {
        Account account = daoAccount.findById(id).get(0);
        if (account == null)
            throw new RuntimeException("invalid account number!");
        else return account;
    }


}
