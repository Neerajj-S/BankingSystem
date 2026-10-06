package org.Application;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Banking System Application ===");
        System.out.println("1. Add a new customer and create a bank account");
        System.out.println("2. Transfer funds between accounts");
        System.out.println("3. View transaction history for an account");
        System.out.println("4. View Customers table");
        System.out.println("5. View Bank Accounts table");
        System.out.println("6. View Transactions table");
        System.out.print("Enter your choice (1-6): ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        DatabaseViewer dbViewer = new DatabaseViewer();

        try {
            if (choice == 1) {
                System.out.print("Enter customer name: ");
                String customerName = scanner.nextLine();
                System.out.print("Enter account number: ");
                String accountNumber = scanner.nextLine();
                System.out.print("Enter initial balance: ");
                double initialBalance = scanner.nextDouble();

                AddCustomer addCustomer = new AddCustomer();
                addCustomer.addnewCustomer(customerName, accountNumber, initialBalance);

            } else if (choice == 2) {
                System.out.print("Enter source account number: ");
                String fromAccountNumber = scanner.nextLine();
                System.out.print("Enter destination account number: ");
                String toAccountNumber = scanner.nextLine();
                System.out.print("Enter amount to transfer: ");
                double amount = scanner.nextDouble();

                TransferService transferService = new TransferService();
                transferService.transferFunds(fromAccountNumber, toAccountNumber, amount);

            } else if (choice == 3) {
                System.out.print("Enter account number to view transaction history: ");
                String accountNumber = scanner.nextLine();

                TransactionHistory transactionHistory = new TransactionHistory();
                transactionHistory.viewTransactionHistory(accountNumber);

            } else if (choice == 4) {
                dbViewer.viewAllCustomers();

            } else if (choice == 5) {
                dbViewer.viewAllBankAccounts();

            } else if (choice == 6) {
                dbViewer.viewAllTransactions();

            } else {
                System.out.println("Invalid choice. Exiting the application.");
            }
        } finally {
            scanner.close();
            Config.getFactory().close();
        }
    }
}