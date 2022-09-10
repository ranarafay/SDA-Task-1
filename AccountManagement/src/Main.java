import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t----------------------------");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t- Account Management System -");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t----------------------------");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t----------------------------");

        int choice = -1;
        do {
            System.out.println("\n------------");
            System.out.println("1: Login");
            System.out.println("2: Signup");
            System.out.println("0: Exit");
            System.out.print("Enter: ");
            choice = sc.nextInt();
            System.out.println("------------");
            switch (choice) {
                case 1:
                    Operations.login();
                    break;
                case 2:
                    Operations.signUp();
                    break;
            }
        } while (choice != 0);
    }
}