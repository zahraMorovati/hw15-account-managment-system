package service;

import dao.DaoUser;
import model.Account;
import model.User;
import model.enumation.AccountType;

import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService{

    DaoUser daoUser = new DaoUser();
    @Override
    public void saveUser(User user) {
        daoUser.save(user);
    }

    @Override
    public User getUserByName(String name) {
        List<User> results = daoUser.findByName(name);
        if(results.isEmpty()){
            throw new RuntimeException("cannot find user by this name!");
        }else {
            return results.get(0);
        }
    }

    @Override
    public User getUserByFamilyName(String familyName) {
        List<User> results = daoUser.findByFamilyName(familyName);
        if(results.isEmpty()){
            throw new RuntimeException("cannot find user by this family name!");
        }else {
            return results.get(0);
        }
    }

    @Override
    public User getUserByCardNumber(int cardNumber) {
        List<User> results = daoUser.findByCardNumber(cardNumber);
        if(results.isEmpty()){
            throw new RuntimeException("cannot find user by this card Number!");
        }else {
            return results.get(0);
        }
    }

    @Override
    public User getUserById(int id) {
        List<User> results = daoUser.findById(id);
        if(results.isEmpty()){
            throw new RuntimeException("cannot find user!");
        }else {
            return results.get(0);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = daoUser.findAll();
        if(userList.isEmpty())
            throw new RuntimeException("there is no user!");
        else return userList;
    }

    @Override
    public void addAccount(int userId, AccountType accountType, double balance) {
        User user = daoUser.findById(userId).get(0);
        if(user!=null) {
            Account account = getAccount(accountType, balance, user);
            daoUser.addAccount(user.getId(), account);
        }else throw new RuntimeException("invalid user id!");
    }

    private Account getAccount(AccountType accountType, double balance, User user) {
        Account account = Account.AccountBuilder.anAccount()
                .setAccountType(accountType)
                .setBalance(balance)
                .setCreateDate(new Date())
                .setUser(user)
                .build();
        return account;
    }
}
