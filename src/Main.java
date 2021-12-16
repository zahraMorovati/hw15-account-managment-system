import dao.MySessionFactory;
import dao.Dao;
import model.User;
import model.enumation.AccountType;
import service.AccountServiceImpl;
import service.TransactionService;
import service.UserServiceImpl;
import utilities.ConsoleColors;
import utilities.GetValidData;

import static utilities.printOutput.*;

public class Main {

    public static void main(String[] args) {

         Dao.creatDatabase();
         MySessionFactory mySessionFactory = new MySessionFactory();
         mainMenu();

    }

    public static void mainMenu(){
        UserServiceImpl userService = new UserServiceImpl();
        AccountServiceImpl accountService = new AccountServiceImpl();
        TransactionService transactionService = new TransactionService();
        try {
            while (true){
                int choice = getChoice();
                switch (choice){
                    case 1:{
                        User user = GetValidData.getUserInfo();
                        userService.saveUser(user);
                        System.out.println("user successfully saved!");
                    }break;
                    case 2:{
                        accountManagmentMenu(accountService,userService);
                    }break;
                    case 3:{
                        String name = GetValidData.getValidName("first name: ");
                        System.out.println(userService.getUserByName(name).toString());
                    }break;
                    case 4:{
                        String family = GetValidData.getValidName("last name: ");
                        System.out.println(userService.getUserByFamilyName(family).toString());
                    }break;
                    case 5:{
                        int cardNumber = GetValidData.getValidInt("card number: ");
                        System.out.println(userService.getUserByCardNumber(cardNumber).toString());
                    }break;
                    case 6:{
                        int userId = getUserId(userService);
                        printUserTransactions(transactionService.getUserTransactionList(userId));

                    }break;
                    case 7:{
                        int userId = getUserId(userService);
                        int accountId = getAccountId(userService.getUserById(userId));
                        printAccountlast3Transactions(transactionService.getAccountTransactionList(accountId));
                    }break;

                    case 8:{
                        System.exit(0);
                    }break;
                }
            }
        }catch (Exception e){
            System.out.println(ConsoleColors.RED+e.getMessage()+ConsoleColors.RESET);
            mainMenu();
        }

    }

    private static int getUserId(UserServiceImpl userService) {
        printUsersList(userService.getAllUsers());
        int userId = GetValidData.getValidInt("user id: ");
        return userId;
    }

    private static int getChoice() {
        int choice = GetValidData.getValidChoice("----------------- main menu -----------------------\n" +
                "1)save user\n" +
                "2)account managent\n" +
                "3)search user by name\n" +
                "4)search user by family name\n" +
                "5)search user by card number\n" +
                "6)user transactions\n" +
                "7)user Account transactions\n" +
                "8)exit\n" +
                "enter yout choice: ",8);
        return choice;
    }

    public static void accountManagmentMenu(AccountServiceImpl accountService,UserServiceImpl userService){
        try {
            while (true){
                int choice = GetValidData.getValidChoice("------------- account managment menu -------------\n" +
                        "1)add account 2)deposit 3)withdraw 4)mainMenu \n" +
                        "enter your choice: ",4);
                switch (choice){
                    case 1:{
                        int userId=getUserId(userService);
                        AccountType accountType= GetValidData.getValidAccountType();
                        double balance = GetValidData.getValidDouble("balance: ");
                        userService.addAccount(userId,accountType,balance);
                        System.out.println("account successfully saved!");
                    }break;
                    case 2:{
                        whithdrawOrDipositOperation(accountService, userService, "deposit amount: ");
                    }break;
                    case 3:{
                        whithdrawOrDipositOperation(accountService, userService, "withdraw amount: ");
                    }break;
                    case 4:{
                        mainMenu();
                    }break;
                }
            }
        }catch (Exception e){
            System.out.println(ConsoleColors.RED+e.getMessage()+ConsoleColors.RESET);
            mainMenu();
        }

    }

    private static void whithdrawOrDipositOperation(AccountServiceImpl accountService, UserServiceImpl userService, String s) {

        int userId = getUserId(userService);
        User user = userService.getUserById(userId);
        int accountId = getAccountId(user);
        double amount = GetValidData.getValidDouble(s);
        accountService.deposit(userId, accountId, amount);
    }

    private static int getAccountId(User user) {
        printUsersAccounts(user);
        return GetValidData.getValidInt("account id: ");
    }


}
