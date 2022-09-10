import java.io.*;
import java.util.*;

public class Operations {
    public static void signUp() throws Exception {
//        for ints
        Scanner sc = new Scanner(System.in);
//        for Strings
        Scanner sc1 = new Scanner(System.in);

//        Collection for storing data
        ArrayList<User> users = new ArrayList<User>();

//        creating a file to store data
        File file = new File("user.txt");

//        for writing data into the file in object form
        ObjectOutputStream oos = null;
//        for reading data from the file in object form
        ObjectInputStream ois = null;

        if (file.isFile()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(file));
                users = (ArrayList<User>) ois.readObject();
                ois.close();
            } catch (EOFException e) {
            }
        }

//        taking account details
        System.out.println("\n-----------");
        System.out.println("- SignUp -");
        System.out.println("-----------");
        System.out.println("1: Saving Account");
        System.out.println("2: Checking Account");
//        Account Type
        System.out.print("Account Type: ");
        int accTypeInt = sc.nextInt();
//        setting account type
        char accType = 'n';
        if (accTypeInt == 1) {
            accType = 's';
        } else if (accTypeInt == 2) {
            accType = 'c';
        }
//        accNumber
        Random rand = new Random(); // generating random account number
        Random r = new Random();
        int low = 900000;
        int high = 999999;
        int accNo = r.nextInt(high - low) + low;
//        name
        System.out.print("Name: ");
        String name = sc1.nextLine();
//        phoneNumber
        System.out.print("Phone Number: ");
        String phoneNumber = sc1.nextLine();
//        address
        System.out.print("Address: ");
        String address = sc1.nextLine();
//        password
        System.out.println();
        System.out.print("Select Password(numbers only): ");
        int password = sc.nextInt();
        System.out.println("-------------------------------------");

//        initializing user with the giving information
        User newUser = new User(accNo, name, phoneNumber, address, password, accType);

//        adding user to the list
        users.add(newUser);

//        writing the data into the file
        oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(users);
        oos.close();

//        showing login details to the user
        System.out.println("\n---------------------");
        System.out.println("SignedUp Successfully");
        System.out.println("---------------------");
        System.out.println("Account Number: " + newUser.getLoginDetails().getAccountNumber());
        System.out.println("Password: " + newUser.getLoginDetails().getPassword());
        System.out.println("----------------------");
        System.out.println("----------------------");
    }
//----------------------------------------------------------------------------------------------------------------------
    public static void login() {
//        taking login details
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----------");
        System.out.println("- LogIn -");
        System.out.println("----------");
        System.out.print("Account Number: ");
        int accNo = sc.nextInt();
        System.out.print("Password: ");
        int pass = sc.nextInt();

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

//        iterating over list of users
        ListIterator li = null;
        li = users.listIterator();
        boolean login = false;
        while (li.hasNext()) {
            User obj = (User) li.next();
            if (obj.getLoginDetails().getAccountNumber() == accNo && obj.getLoginDetails().getPassword() == pass) {
                login = true;
                System.out.println("------------------");
                System.out.println("Login Successful");
                System.out.println("------------------");
                System.out.println("------------------");


//                asking for further functions
                boolean login1 = true;
                Scanner sc1 = new Scanner(System.in);
                int choice = -1;
                do {
                    System.out.println("\n\n---------------");
                    System.out.println("- Operations -");
                    System.out.println("---------------");
                    System.out.println("1: Make Deposit");
                    System.out.println("2: Make Withdrawal");
                    System.out.println("3: Check Balance");
                    System.out.println("4: Transfer Amount");
                    System.out.println("5: Print Statement");
                    System.out.println("6: Calculate Zakat");
                    System.out.println("7: Display Deductions");
                    System.out.println("8: Change Interest Rate");
                    System.out.println("9: Calculate Interest");
                    System.out.println("10: Delete Account");
                    System.out.println("0: Exit");
                    System.out.print("Enter: ");
                    choice = sc1.nextInt();
                    System.out.println("--------------------");

                    switch (choice) {
                        case 1:
                            try {
                                obj.getAccount().makeDeposit(obj.getLoginDetails().getAccountNumber());
                            } catch (Exception e) {
                            }
                            break;
                        case 2:
                            try {
                                obj.getAccount().makeWithdrawal(obj.getLoginDetails().getAccountNumber());
                            } catch (Exception e) {
                            }
                            break;
                        case 3:
                            try {
                                obj.getAccount().checkBalance(obj.getLoginDetails().getAccountNumber());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 4:
                            try {
                                obj.getAccount().transferAmount(obj.getLoginDetails().getAccountNumber());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 5:
                            obj.getAccount().printStatement(obj.getLoginDetails().getAccountNumber());
                            break;
                        case 6:
                            try {
                                obj.getAccount().calculateZakat(obj.getLoginDetails().getAccountNumber());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 7:
                            obj.getAccount().displayAllDeductions(obj.getLoginDetails().getAccountNumber());
                            break;
                        case 8:
                            try {
                                obj.getAccount().changeInterestRate();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 9:
                            try {
                                obj.getAccount().calculateInterest(obj.getLoginDetails().getAccountNumber());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 10:
                            try {
                                if (obj.getAccount().deleteAccount(obj.getLoginDetails().getAccountNumber())){
                                    choice = 0;
                                }
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            break;
                    }
                } while (choice != 0);
                break;
            }
        }
        if (login == false) {
            System.out.println("------------------------------------------");
            System.out.println("No record Found, kindly SignUp for Login.");
            System.out.println("------------------------------------------");
            System.out.println("------------------------------------------");
        }
    }
}