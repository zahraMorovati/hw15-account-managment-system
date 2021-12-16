package service;

import model.Account;
import model.User;
import model.enumation.AccountType;

import java.util.List;

public interface UserService {

    void saveUser(User user);
    User getUserByName(String name);
    User getUserByFamilyName(String familyName);
    User getUserByCardNumber(int cardNumber);
    User getUserById(int id);
    List<User> getAllUsers();
    void addAccount(int userId, AccountType accountType, double balance);




}
