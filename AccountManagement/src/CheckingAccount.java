import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

//implementing Inheritance
public class CheckingAccount extends BankAccount {
    @Override
    public void makeDeposit(int accNo) throws Exception {
        System.out.println("- Deposit - ");
        System.out.println("-----------");
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
//                taking amount for deposit
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter amount: ");
                int amount = sc.nextInt();

//                getting previous balance and adding deposited amount
                long balance = obj.getAccount().getBalance();

//                adding deposited amount
                balance += amount;

//               checking for transaction count
                if (obj.getTransactionCount() >= 2) {
//                   deducting transaction fees
                    balance -= 10;
                }


//                updating the balance in te array list of the users
                if (balance > 0) {
//                    setting balance
                    obj.getAccount().setBalance(balance);
//                    adding transaction count
                    obj.addTransactionCount();

//                    adding transaction details
//                   initializing date and time objects for getting date and time
                    LocalDate date = LocalDate.now();
                    LocalTime time = LocalTime.now();

//                    for storing transaction and deduction details
                    String transactionDetail;
                    String deductionDetail = "";

                    if (obj.getTransactionCount() > 2) {
                        transactionDetail = "Deposited " + amount + " - 10Rs(transaction fee) = " + (amount - 10) + "Rs at " + date + "(Y/M/D) " + time + "(h:m:s)\n";
                        deductionDetail = "Deposit Fee: 10Rs(transaction fee) " + "at " + date + "(Y/M/D) " + time + "(h:m:s)\n";
                    } else {
                        transactionDetail = "Deposited " + amount + "Rs at " + date + "(Y/M/D) " + time + "(h:m:s)\n";
                    }

                    obj.addTransactionDetail(transactionDetail);
                    obj.addDeductionDetail(deductionDetail);
                    System.out.print(transactionDetail);
                    System.out.println("--------------------------------------------");
                    System.out.println("--------------------------------------------");
                }
                else {
                    System.out.println("Error, can not deposit balance is >= 0.");
                    System.out.println("----------------------------------------");
                    System.out.println("----------------------------------------");
                }

//                updating the file
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(users);
                oos.close();

//                breaking the loop
                break;
            }
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void makeWithdrawal(int accNo) throws Exception {
        System.out.println("- Withdrawal -");
        System.out.println("------------");

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
//                taking amount for deposit
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter amount: ");
                int amount = sc.nextInt();

//                getting previous balance and adding deposited amount
                long balance = obj.getAccount().getBalance();

//                subtracting deposited amount
                balance -= amount;

//                checking for transaction count
                if (obj.getTransactionCount() > 2) {
//                   deducting transaction fees
                    balance -= 10;
                }

//                updating the balance in te array list of the users
                if (balance > 0) {
//                    setting balance
                    obj.getAccount().setBalance(balance);
//                    adding transaction count
                    obj.addTransactionCount();

//                    adding transaction details
//                   initializing date and time objects for getting date and time
                    LocalDate date = LocalDate.now();
                    LocalTime time = LocalTime.now();

//                    for storing transaction and deduction details
                    String transactionDetail = "";
                    String deductionDetail;

                    if (obj.getTransactionCount() > 2) {
                        transactionDetail = "Withdrew " + amount + "Rs at " + date + "(Y/M/D) " + time + "(h:m:s)\n";
                        obj.addTransactionDetail(transactionDetail);

                        deductionDetail = "Withdraw fee: 10Rs(transaction fee) " + "at " + date + "(Y/M/D) " + time + "(h:m:s)\n";
                        obj.addDeductionDetail(deductionDetail);
                    }
                    else {
                        transactionDetail = "Withdrew " + amount + "Rs at " + date + "(Y/M/D) " + time + "(h:m:s)\n";
                        obj.addTransactionDetail(transactionDetail);
                    }

                    System.out.print(transactionDetail);
                    System.out.println("Current Balance: " + balance);
                    System.out.println("------------------------------");
                    System.out.println("------------------------------");
                }

                else {
                    System.out.println("Error! your balance is: " + obj.getAccount().getBalance());
                    System.out.println("-------------------------------");
                    System.out.println("-------------------------------");

                }

//                updating the file
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(users);
                oos.close();
                break;
            }
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void calculateZakat(int accNo){
        System.out.println("- Zakat Calculation -");
        System.out.println("----------------------");

        System.out.println("Cannot calculate Zakat your account type is Checking.");
        System.out.println("-----------------------------------------------------");
        System.out.println("-----------------------------------------------------");
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void calculateInterest(int accNo){
        System.out.println("- Interest -");
        System.out.println("--------------");

        System.out.println("Interest doesn't apply on checking accounts.");
        System.out.println("-----------------------------------------------------");
        System.out.println("-----------------------------------------------------");
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void changeInterestRate(){
        System.out.println("- Change Interest -");
        System.out.println("--------------------");

        System.out.println("Interest doesn't apply on checking accounts.");
        System.out.println("-----------------------------------------------------");
        System.out.println("-----------------------------------------------------");
    }
}
