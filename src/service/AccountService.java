package service;

import model.Account;
import model.User;
import model.enumation.AccountType;

import java.util.List;

public interface AccountService {

    void withdraw(int userId,int accountId, double withdrawAmount);
    void deposit(int userId,int accountId, double depositAmount);
    List<Account> getAllAccouts(int userId);
    Account getAccountById(int id);
}
