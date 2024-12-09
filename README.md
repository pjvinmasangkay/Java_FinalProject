# FinanceWise: Personal Finance Management System

## I. Project Overview

FinanceWise is a console-based application designed to help individuals manage their personal finances by tracking income, expenses, and providing insights into financial summaries. The system allows users to create accounts, log in, add transactions, view transaction histories, delete transactions, and get a summary of their financial activities. All account and transaction data is saved and loaded from a text file to ensure persistence between sessions. This project leverages Object-Oriented Programming principles to ensure maintainability and flexibility.

---

## II. Explanation of how OOP principles were applied

FinanceWise demonstrates the core principles of Object-Oriented Programming, including Abstraction, Encapsulation, Inheritance, and Polymorphism.

### Abstraction
- The `Transaction` class defines a blueprint for income and expense transactions, specifying common attributes like `amount` and behaviors (e.g., `getType()`).
- The `getType()` method is declared as abstract, allowing `IncomeTransaction` and `ExpenseTransaction` subclasses to provide specific implementations.

### Encapsulation
- Fields like `username`, `password`, and `transactions` are marked as private to prevent unauthorized access.
- Public getter and setter methods control access to these fields, maintaining data integrity.
- The `addTransaction` and `deleteTransaction` methods ensure that modifications to the transaction list occur only through defined methods, maintaining data consistency.

### Inheritance
- The `IncomeTransaction` and `ExpenseTransaction` classes inherit from the abstract `Transaction` class.
- Both subclasses reuse the `amount` property and the `getAmount()` method from the parent class, reducing code duplication.

### Polymorphism
- The `getType()` method in the `Transaction` class is overridden in its subclasses to provide specific behavior ("income" or "expense").
- The `viewTransactions` method leverages polymorphism by treating all transactions as `Transaction` objects. The actual implementation of `getType()` is determined at runtime.

---

## III. Details of the chosen SDG and its integration into the project

### SDG 8 – Decent Work and Economic Growth
This goal aims to promote sustained, inclusive, and sustainable economic growth, full and productive employment, and decent work for all. It emphasizes fostering financial literacy, ensuring equitable access to resources, and enabling individuals to manage their economic activities effectively.

FinanceWise aligns with SDG 8 – Decent Work and Economic Growth by providing individuals with tools to manage their finances effectively and promoting financial literacy. Through features like transaction tracking, income-expense categorization, and financial summaries, it enables users to understand their spending habits, make better decisions, and achieve better financial stability.

With a simple, user-friendly application, FinanceWise ensures inclusivity, allowing users of all backgrounds and technical expertise to access and benefit from financial management tools. By encouraging transparency, accountability, and responsible economic behavior, the system contributes to individual financial empowerment and, ultimately, to broader sustainable economic growth and productivity.

---

## IV. Instructions for running the program

Upon starting, the program displays a welcome screen with three options:
1. **Login**: Enter your username and password to access your account.
2. **Create Account**: Register a new account with a username and password.
3. **Exit**: Close the program.

### Dashboard Options
After logging in, the dashboard allows you to:

#### Add Income or Expense Transactions
Allows users to record financial activities by categorizing them as either income or expenses. Users input a monetary amount, which is saved as a transaction under the appropriate category (income or expense).

#### View All Transactions
Provides users with an overview of their financial activity, listing all transactions in chronological order. The program displays each transaction's type (income/expense) and amount, along with a numbered index for reference.

#### Delete Transactions
Enables users to remove unnecessary or incorrect transactions from their records. Users specify the transaction's index from the displayed list.

#### View a Summary of Income, Expenses, and Balance
The program calculates and displays:
- **Total Income**: Sum of all income transactions.
- **Total Expenses**: Sum of all expense transactions.
- **Balance**: Difference between total income and total expenses.

#### Logout to Return to the Main Menu
Users can log out from their account, preventing unauthorized access to financial data.
