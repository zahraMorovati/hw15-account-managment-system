package utilities;

import model.Card;
import model.User;
import model.enumation.AccountType;
import model.enumation.TypeUser;
import org.hibernate.usertype.UserType;
import utilities.ConsoleColors;

import java.util.InputMismatchException;
import java.util.Scanner;


import static utilities.ConsoleColors.*;


public class GetValidData {

    public static Scanner input = new Scanner(System.in);

    public static String getValidName(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String name = input.next();
        if (name.matches("[a-zA-Z]*")) {
            return name;
        } else {
            System.out.println(RED + "invalid input!" + RESET);
            return getValidName(message);
        }
    }

    public static String getValidString(String message){
        System.out.print(BLUE_BRIGHT + message + RESET);
        String str = input.next();
        return str;
    }


    public static String getValidPhoneNumber(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String phoneNumber = input.next();
        String str = phoneNumber.substring(0);
        if (isNumeric(str)) {
            return phoneNumber;
        } else {
            System.out.println(RED + "invalid phone number!" + RESET);
            return getValidPhoneNumber(message);
        }
    }

    public static int getValidInt(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String str = input.next();
        if (isNumeric(str)) {
            return Integer.parseInt(str);
        } else {
            System.out.println(RED + "invalid input!" + RESET);
            return getValidInt(message);
        }
    }

    public static double getValidDouble(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String str = input.next();
        if (isNumeric(str)) {
            return Double.parseDouble(str);
        } else {
            System.out.println(RED + "invalid input!" + RESET);
            return getValidDouble(message);
        }
    }

    public static int getValidChoice(String message, int maxChoice) {
        int number = getValidInt(message);
        for (int i = 1; i < maxChoice + 1; i++) {
            if (i == number) {
                return number;
            }
        }
        System.out.println(RED + "invalid choice!" + RESET);
        return getValidChoice(message, maxChoice);
    }

    public static boolean isNumeric(String str) {

        if (str == null || str.length() == 0) {
            return false;
        }

        try {
            Double.parseDouble(str);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static TypeUser getValidUserType(){
        int choice=getValidChoice("1)good dealer 2)bad dealer : ",2);
        if(choice==1)
            return TypeUser.GOOD_DEALER;
        else return TypeUser.BAD_DEALER;
    }
    public static AccountType getValidAccountType(){
        int choice=getValidChoice("1)short term 2)long term : ",2);
        if(choice==1)
            return AccountType.SHORTTEERM;
        else return AccountType.LONGTERM;
    }

    public static User getUserInfo(){
        String name=getValidName("name: ");
        String family=getValidName("family name: ");
        int nationalCode=getValidInt("national code: ");
        TypeUser userType=getValidUserType();
        int cardNumber = getValidInt("card number: ");
        int cvv2=getValidInt("cvv2: ");
        Card card=Card.CardBuilder.aCard().setCardNumber(cardNumber).setCvv2(cvv2).build();
        return setUser(name, family, nationalCode, userType, card);
    }

    private static User setUser(String name, String family, int nationalCode, TypeUser userType, Card card) {
        User user = User.UserBuilder.anUser()
                .setName(name)
                .setFamily(family)
                .setNationalCode(nationalCode)
                .setUserType(userType)
                .setCard(card).build();
        return user;
    }



}
