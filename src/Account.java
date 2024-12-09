import java.io.*;
import java.util.*;

class Account {
    private String username;
    private String password;
    private List<Transaction> transactions; 

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.transactions = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    
    public void deleteTransaction(int index) {
        if (index >= 0 && index < transactions.size()) {
            transactions.remove(index);
        }
    }

    public static void saveAccounts(Map<String, Account> accounts) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt"))) {
            for (Account account : accounts.values()) {
                writer.write(account.getUsername() + "," + account.getPassword());
                writer.newLine();
                for (Transaction transaction : account.getTransactions()) {
                    writer.write("  " + transaction.getType() + "," + transaction.getAmount());
                    writer.newLine();
                }
            }
        }
    }

    // Load all accounts from a text file
    public static Map<String, Account> loadAccounts() throws IOException {
        Map<String, Account> accounts = new HashMap<>();
        File file = new File("accounts.txt");
        if (!file.exists()) {
            // Create the file if it doesn't exist
            file.createNewFile();
            System.out.println("accounts.txt file created.");
        } else {
            // Load existing accounts if the file exists
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                Account currentAccount = null;
                while ((line = reader.readLine()) != null) {
                    line = line.trim(); // Trim spaces
                    if (line.startsWith("  ")) {
                        // This is a transaction line
                        String[] parts = line.split(",");
                        if (parts.length == 2 && currentAccount != null) {
                            String type = parts[0].trim();
                            double amount = Double.parseDouble(parts[1].trim());

                            // Based on the type, add the correct transaction
                            if (type.equals("income")) {
                                currentAccount.addTransaction(new IncomeTransaction(amount));
                            } else if (type.equals("expense")) {
                                currentAccount.addTransaction(new ExpenseTransaction(amount));
                            }
                        }
                    } else {
                        
                        String[] parts = line.split(",");
                        if (parts.length == 2) {
                            String username = parts[0].trim();
                            String password = parts[1].trim();
                            currentAccount = new Account(username, password);
                            accounts.put(username, currentAccount);
                        }
                    }
                }
            }
        }
        return accounts;
    }
}
