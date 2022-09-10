import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

//implementing Inheritance
public class SavingAccount extends BankAccount {
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

//                checking for checking account
                if (obj.getAccount().getAccType() == 'c') {
//                    checking for transaction count
                    if (obj.getTransactionCount() >= 2) {
//                        deducting transaction fees
                        balance -= 10;
                    }
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

                    transactionDetail = "Deposited " + amount + "Rs at " + date + "(Y/M/D) " + time + "(h:m:s)\n";
                    obj.addTransactionDetail(transactionDetail);

                    System.out.print(transactionDetail);
                    System.out.println("--------------------------------------------");
                    System.out.println("--------------------------------------------");
                } else {
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

                    transactionDetail = "Withdrew " + amount + "Rs at " + date + "(Y/M/D) " + time + "(h:m:s)\n";
                    obj.addTransactionDetail(transactionDetail);


                    System.out.print(transactionDetail);
                    System.out.println("Current Balance: " + balance);
                    System.out.println("------------------------------");
                    System.out.println("------------------------------");
                } else {
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
    public void calculateZakat(int accNo) throws Exception {
        System.out.println("- Zakat Calculation -");
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

                long balance = obj.getAccount().getBalance();

//                    checking if balance is >= 20000
                if (balance >= 20000) {
                    long zakat = (long) ((balance * 2.5) / 100);
                    System.out.println("Zakat = " + "(" + balance + " x " + "2.5) / 100 => " + zakat);

                    System.out.print("Do you want to pay zakat(y/n): ");
                    Scanner sc = new Scanner(System.in);
                    String chk = sc.next();
                    if (chk.equals("y") || chk.equals("Y")) {
                        obj.getAccount().setBalance((obj.getAccount().getBalance()) - zakat);

                        System.out.println(zakat + "Rs deducted as Zakat");
                        System.out.println("------------------------------------------");
                        System.out.println("------------------------------------------");
//                        adding transaction details
//                        initializing date and time objects for getting date and time
                        LocalDate date = LocalDate.now();
                        LocalTime time = LocalTime.now();
//                        storing deduction details
                        String deductionDetail = "Zakat Deduction: " + zakat + " deducted at " + date + "(Y/M/D) " + time + "(h:m:s)\n";
                        obj.addDeductionDetail(deductionDetail);
                    } else {
                        System.out.println("----------------------------------------------");
                        System.out.println("----------------------------------------------");
                    }
                } else {
                    System.out.println("Cannot calculate Zakat balance is " + balance);
                    System.out.println("----------------------------------------------");
                    System.out.println("----------------------------------------------");
                    System.out.println();
                }

//                updating the file
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(users);
                oos.close();
//                breaking loop
                break;
            }
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void changeInterestRate() throws Exception {
        System.out.println("- Change Interest Rate -");
        System.out.println("-------------------------");

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

//        taking new interest rate
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter new Interest Rate: ");
        int newInterestRate = sc.nextInt();
        System.out.println("---------------------------");
        System.out.println("---------------------------");

//        iterating over the list of users
        ListIterator li = null;
        li = users.listIterator();
        boolean login = false;
        while (li.hasNext()) {
            User obj = (User) li.next();

//          changing interest rate for all users
            obj.setInterestRate(newInterestRate);

//          updating the file
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(users);
            oos.close();
//          breaking loop
            break;
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    public void calculateInterest(int accNo) throws Exception {
        System.out.println("- Interest -");
        System.out.println("--------------");

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
        while (li.hasNext()) {
            User obj = (User) li.next();
            if (obj.getLoginDetails().getAccountNumber() == accNo) {

                long crBalance = obj.getAccount().getBalance();
                float intRate = (obj.getInterestRate()) / 100;

                long interest = (long) (intRate * crBalance);

                System.out.println("Interest is: " + interest);

                System.out.print("Do you want to pay interest(y/n): ");
                Scanner sc = new Scanner(System.in);
                String chk = sc.next();
                if (chk.equals("y") || chk.equals("Y")) {
                    obj.getAccount().setBalance((obj.getAccount().getBalance()) - interest);

                    System.out.println(interest + "Rs deducted as Interest");
                    System.out.println("------------------------------------------");
                    System.out.println("------------------------------------------");
//                        adding transaction details
//                        initializing date and time objects for getting date and time
                    LocalDate date = LocalDate.now();
                    LocalTime time = LocalTime.now();
//                        storing deduction details
                    String deductionDetail = "Interest Deduction: " + interest + " deducted at " + date + "(Y/M/D) " + time + "(h:m:s)\n";
                    obj.addDeductionDetail(deductionDetail);

//                updating the file
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(users);
                    oos.close();
                } else {
                    System.out.println("----------------------------------------------");
                    System.out.println("----------------------------------------------");
                }

//                breaking loop
                break;
            }
        }
    }
}


