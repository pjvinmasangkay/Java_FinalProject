import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        FinanceWise financeWise = new FinanceWise();
        Scanner scanner = new Scanner(System.in);
        

        System.out.println("=== Welcome to FinanceWise: Personal Finance Management System ===");
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("\n1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    loggedIn = financeWise.login(username, password);
                    if (loggedIn) {
                        financeWise.showMenu();
                    }
                    break;
                case 2:
                    // create acc
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    String password1;
                    String password2;

                    do {
                        System.out.print("Enter password: ");
                        password1 = scanner.nextLine();

                        // checks if password is empty
                        if (password1.trim().isEmpty()) {
                            System.out.println("Password cannot be empty or contain only spaces. Please enter a valid password.");
                            continue;
                        }

                        System.out.print("Re-enter password: ");
                        password2 = scanner.nextLine();

                        if (!password1.equals(password2)) {
                            System.out.println("Passwords do not match. Please try again.");
                        }
                    } while (!password1.equals(password1) || password1.trim().isEmpty());

                    financeWise.createAccount(username, password1);
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    System.exit(0); 
                    break;
                default:
                    System.out.println("Invalid option.");
            }
            
        }
       scanner.close();
    }
    
}
