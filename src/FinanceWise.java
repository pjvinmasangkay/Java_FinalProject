import java.io.*;
import java.util.*;

class FinanceWise {
    private Map<String, Account> accounts = new HashMap<>();
    private Account currentAccount;
    private Scanner scanner; 

    public FinanceWise() {
        try {
            accounts = Account.loadAccounts();
        } catch (IOException e) {
            System.out.println("Error loading accounts.");
        }
        scanner = new Scanner(System.in); 
    }

    public void createAccount(String username, String password) {
        if (accounts.containsKey(username)) {
            System.out.println("Username already exists.");
        } else {
            Account newAccount = new Account(username, password);
            accounts.put(username, newAccount);
            try {
                Account.saveAccounts(accounts);
                System.out.println("Account created successfully!");
            } catch (IOException e) {
                System.out.println("Error saving account.");
            }
        }
    }

    public boolean login(String username, String password) {
        Account account = accounts.get(username);
        if (account != null && account.getPassword().equals(password)) {
            currentAccount = account;
            System.out.println("Login successful!");
            return true;
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    public void showMenu() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n=== Welcome, " + currentAccount.getUsername() + " ===");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Transactions");
            System.out.println("4. Delete Transaction");
            System.out.println("5. View Summary");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter income amount: ");
                    double income = scanner.nextDouble();
                    currentAccount.addTransaction(new IncomeTransaction(income));
                    System.out.println("Income added!");
                    break;
                case 2:
                    System.out.print("Enter expense amount: ");
                    double expense = scanner.nextDouble();
                    currentAccount.addTransaction(new ExpenseTransaction(expense));
                    System.out.println("Expense added!");
                    break;
                case 3:
                    viewTransactions();
                    break;
                case 4:
                    deleteTransaction();
                    break;
                case 5:
                    viewSummary();
                    break;
                case 6:
                    loggedIn = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
            try {
                Account.saveAccounts(accounts);
            } catch (IOException e) {
                System.out.println("Error saving transactions.");
            }
        }
    }

    private void viewTransactions() {
        System.out.println("\n=== Transactions for " + currentAccount.getUsername() + " ===");
        List<Transaction> transactions = currentAccount.getTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions to show.");
        } else {
            for (int i = 0; i < transactions.size(); i++) {
                Transaction transaction = transactions.get(i);
                System.out.println((i + 1) + ". " + transaction.getType() + ": " + transaction.getAmount());
            }
        }
    }

    private void deleteTransaction() {
        viewTransactions();
        if (currentAccount.getTransactions().isEmpty()) {
            return;
        }
        System.out.print("Enter the number of the transaction to delete: ");
        int transactionIndex = scanner.nextInt() - 1; // Use the same Scanner instance
        if (transactionIndex >= 0 && transactionIndex < currentAccount.getTransactions().size()) {
            currentAccount.deleteTransaction(transactionIndex);
            System.out.println("Transaction deleted!");
        } else {
            System.out.println("Invalid transaction number.");
        }
    }

    private void viewSummary() {
        double totalIncome = 0;
        double totalExpense = 0;
        for (Transaction transaction : currentAccount.getTransactions()) {
            if (transaction.getType().equalsIgnoreCase("income")) { 
                totalIncome += transaction.getAmount();
            } else {
                totalExpense += transaction.getAmount();
            }
        }
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expense: " + totalExpense);
        System.out.println("Balance: " + (totalIncome - totalExpense));
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close(); 
        }
    }
}
