package utilities;

import model.Transaction;
import model.User;

import java.util.List;

public class printOutput {

    public static void printUsersList(List<User> userList) {
        if (userList.isEmpty())
            throw new RuntimeException("empty user list!");
        else
            userList.stream().map((i) -> i.getId() + ")" + i.getName() + " " + i.getFamily()).forEach(i -> System.out.println(i));
    }

    public static void printUsersAccounts(User user) {
        if (user != null) {
            System.out.println(user.getName() + " " + user.getFamily() + " accounts: ");
            user.getAccountList().stream().map((i) -> i.getId() + ") balance: " + i.getBalance() + " " + i.getAccountType()).forEach(i -> System.out.println(i));
        } else throw new RuntimeException("cannot find user!");
    }

    public static void printUserTransactions(List<Transaction> transactions) {
        if (!transactions.isEmpty()) {
            transactions.stream()
                    .map((i) -> i.getId() + ") " + i.getTransactionType() + " " + i.getModifyDate()).forEach(i -> System.out.println(i));
        } else throw new RuntimeException("cannot find user!");
    }

    public static void printAccountlast3Transactions(List<Transaction> transactions) {
        if (transactions.isEmpty())
            throw new RuntimeException("there is no transaction!");
        else {
            int trasactionListSize = transactions.size();
            List<Transaction> transactionList;
            if (trasactionListSize > 3) {
                transactionList = transactions.subList(trasactionListSize - 3, trasactionListSize);
            } else {
                transactionList = transactions;
            }
            transactionList.stream()
                    .map((i) -> i.getId() + ") " + i.getTransactionType() + " " + i.getModifyDate()).forEach(i -> System.out.println(i));
        }
    }
}
