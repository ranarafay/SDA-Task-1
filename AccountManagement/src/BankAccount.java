import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Scanner;

// implementing Abstraction
public abstract class BankAccount implements Serializable {
    private long balance;
    private char accType;

    //    setters
    public void setBalance(long balance) {
        this.balance = balance;
    }
    public void setAccType(char accType) {
        this.accType = accType;
    }

//    getters
    public long getBalance() {
        return balance;
    }
    public char getAccType() {
        return accType;
    }

//    operations on account for user
//    abstract methods (different) for Saving and Checking account
    public abstract void makeDeposit(int accNo)throws Exception;
    public abstract void makeWithdrawal(int accNo)throws Exception;
    public abstract void calculateZakat(int accNo) throws Exception;
    public abstract void changeInterestRate() throws Exception;
    public abstract void calculateInterest(int accNo) throws Exception;

//    General functions (same) for both accounts
    public void checkBalance(int accNo) {
        System.out.println("- Balance Inquiry -");
        System.out.println("--------------------");

//        for reading objects from file
        ObjectInputStream ois = null;

//        making an ArrayList to store data
        ArrayList<User> users = new ArrayList<User>();

        File file = new File("user.txt");
        if (file.isFile()) {
            try {
//                reading data from file in objects form
                ois = new ObjectInputStream(new FileInputStream(file));
                users = (ArrayList<User>) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
            }
        }

//        iterating over the list of users
        ListIterator li = null;
        li = users.listIterator();
        boolean login = false;
        while (li.hasNext()) {
            User obj = (User) li.next();

//            printing balance if user matches
            if (obj.getLoginDetails().getAccountNumber() == accNo) {
                System.out.println("Name: " + obj.getName());
                System.out.println("Balance: " + obj.getAccount().getBalance());
                System.out.println("------------------");
                System.out.println("------------------");

//                breaking loop
                break;
            }
        }
    }
    public void transferAmount(int accNo) throws Exception {
        System.out.println("- Transfer Amount -");
        System.out.println("----------------------");

//        for reading objects from file
        ObjectInputStream ois = null;

//        making an ArrayList to store data
        ArrayList<User> users = new ArrayList<User>();

        File file = new File("user.txt");
        if (file.isFile()) {
            try {
//                reading data from file in objects form
                ois = new ObjectInputStream(new FileInputStream(file));
                users = (ArrayList<User>) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
            }
        }

//        iterating over the list of users
        ListIterator li = null;
        li = users.listIterator();
        String transactionDetail;
        int trAccNo = 0;
        long trAmount = 0;
        boolean login = false;
        boolean balCheck = false;
        while (li.hasNext()) {
            User obj = (User) li.next();
            if (obj.getLoginDetails().getAccountNumber() == accNo) {
                login = true;
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter account for transfer: ");
                trAccNo = sc.nextInt();
                System.out.print("Enter amount for transfer: ");
                trAmount = sc.nextLong();

                long crAmount = obj.getAccount().getBalance();


                if (trAmount < crAmount){
                    balCheck = true;
                    long crBalance = obj.getAccount().getBalance();
                    obj.getAccount().setBalance(crBalance - trAmount);

//                       updating the file
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(users);
                    oos.close();

//                   breaking loop
                    break;
                }
                else {
                    System.out.println("\nCannot transfer, your balance is: " + obj.getAccount().getBalance());
                }
            }
        }

        if (!login){
            System.out.println("Account not found.");
            System.out.println("--------------------");
            System.out.println("--------------------");
        }

        boolean trasnfer = false;
        if (balCheck){
            ListIterator<User> li1 = users.listIterator();
//         for transfer account
            while (li1.hasNext()) {
                User obj = (User) li1.next();
                if (obj.getLoginDetails().getAccountNumber() == trAccNo) {
                    trasnfer = true;
                    long crBalance = obj.getAccount().getBalance();

                    obj.getAccount().setBalance(crBalance + trAmount);

                    System.out.println(trAmount + " Rs to " + trAccNo + " Successfully");
                    System.out.println("-------------------------------------");
                    System.out.println("-------------------------------------");

//                       updating the file
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(users);
                    oos.close();

//                   breaking loop
                    break;
                }
            }
        }
        if (!trasnfer){
            System.out.println("Account for Transfer not found");
            System.out.println("------------------------------");
            System.out.println("------------------------------");
        }
    }
    public void printStatement(int accNo) {
        System.out.println("- Transaction Details -");
        System.out.println("-----------------------");

//        for reading objects from file
        ObjectInputStream ois = null;

//        making an ArrayList to store data
        ArrayList<User> users = new ArrayList<User>();

        File file = new File("user.txt");
        if (file.isFile()) {
            try {
//                reading data from file in objects form
                ois = new ObjectInputStream(new FileInputStream(file));
                users = (ArrayList<User>) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
            }
        }

//        iterating over the list of users
        ListIterator li = null;
        li = users.listIterator();
        boolean login = false;
        while (li.hasNext()) {
            User obj = (User) li.next();
            if (obj.getLoginDetails().getAccountNumber() == accNo) {
                if (obj.getAccount().getAccType() == 's') {
                    System.out.println("Account Type: Saving Account");
                }else if (obj.getAccount().getAccType() == 'c') {
                    System.out.println("Account Type: Checking Account");
                }
                System.out.println("Account Number: " + obj.getLoginDetails().getAccountNumber());
                System.out.println("Name: " + obj.getName());
                System.out.println("Phone No: " + obj.getPhoneNumber());
                System.out.println("Address: " + obj.getAddress());
                System.out.println("Balance: " + obj.getAccount().getBalance());
                System.out.println("----------------------------------------------------------------");
                System.out.print(obj.getTransactionDetails());
                System.out.println("----------------------------------------------------------------");
                System.out.println("----------------------------------------------------------------");

//                breaking loop
                break;
            }
        }
    }
    public void displayAllDeductions(int accNo) {
        System.out.println("- Deduction Details -");
        System.out.println("----------------------");

//        for reading objects from file
        ObjectInputStream ois = null;

//        making an ArrayList to store data
        ArrayList<User> users = new ArrayList<User>();

        File file = new File("user.txt");
        if (file.isFile()) {
            try {
//                reading data from file in objects form
                ois = new ObjectInputStream(new FileInputStream(file));
                users = (ArrayList<User>) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
            }
        }

//        iterating over the list of users
        ListIterator li = null;
        li = users.listIterator();
        boolean login = false;
        while (li.hasNext()) {
            User obj = (User) li.next();
            if (obj.getLoginDetails().getAccountNumber() == accNo) {
                System.out.println("Name: " + obj.getName());
                System.out.print(obj.getDeductionDetails());
                System.out.println("-------------------------");
                System.out.println("-------------------------");

//                breaking loop
                break;
            }
        }
    }
    public boolean deleteAccount(int accNo) throws Exception {
        System.out.println("- Delete Account -");
        System.out.println("-------------------");

//        for reading objects from file
        ObjectInputStream ois = null;

//        making an ArrayList to store data
        ArrayList<User> users = new ArrayList<User>();

        File file = new File("user.txt");
        if (file.isFile()) {
            try {
//                reading data from file in objects form
                ois = new ObjectInputStream(new FileInputStream(file));
                users = (ArrayList<User>) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
            }
        }

//        iterating over the list of users
        ListIterator li = null;
        li = users.listIterator();
        boolean deleted = false;
        boolean found = false;
        while (li.hasNext()) {
            User obj = (User) li.next();

            if (obj.getLoginDetails().getAccountNumber() == accNo) {
                found = true;
//              asking for confirmation
                Scanner sc = new Scanner(System.in);
                System.out.print("Do you want to delete account(y/n): ");
                String chk = sc.next();

                if (Objects.equals(chk, "y") || Objects.equals(chk, "Y")) {
//                    removing account
                    li.remove();
                    deleted = true;
                }
                System.out.println("---------------------------");
                System.out.println("---------------------------");

//              breaking loop
                break;
            }
        }
        if (deleted) {
//          updating the file
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(users);
            oos.close();
            System.out.println("Account deleted successfully.");
            System.out.println("-----------------------------");
            System.out.println("-----------------------------");
        }
        if (!found) {
            System.out.println("Account not found.");
            System.out.println("-------------------");
            System.out.println("-------------------");
        }
        return deleted;
    }
}