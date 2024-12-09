// abstraction
abstract class Transaction {
    protected double amount;

    public Transaction(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    
    public abstract String getType();
}

class IncomeTransaction extends Transaction {
    public IncomeTransaction(double amount) {
        super(amount);
    }

    @Override
    public String getType() {
        return "income";
    }
}

class ExpenseTransaction extends Transaction {
    public ExpenseTransaction(double amount) {
        super(amount);
    }

    @Override
    public String getType() {
        return "expense";
    }
}
