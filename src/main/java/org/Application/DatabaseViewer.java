package org.Application;

import java.util.List;
import org.hibernate.Session;

public class DatabaseViewer {

    // View all Customers
    public void viewAllCustomers() {
        Session session = Config.getSession();
        try {
            session.beginTransaction();
            List<Customer> customers = session.createQuery("FROM Customer", Customer.class).getResultList();

            System.out.println("\n=== CUSTOMERS TABLE ===");
            if (customers.isEmpty()) {
                System.out.println("No customers found.");
            } else {
                System.out.printf("%-15s | %-20s%n", "Customer ID", "Customer Name");
                System.out.println("----------------------------------------");
                for (Customer c : customers) {
                    System.out.printf("%-15d | %-20s%n", c.getCustomer_id(), c.getCustomer_name());
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // View all Bank Accounts
    public void viewAllBankAccounts() {
        Session session = Config.getSession();
        try {
            session.beginTransaction();
            List<Bank_Accounts> accounts = session.createQuery("FROM Bank_Accounts", Bank_Accounts.class).getResultList();

            System.out.println("\n=== BANK ACCOUNTS TABLE ===");
            if (accounts.isEmpty()) {
                System.out.println("No bank accounts found.");
            } else {
                System.out.printf("%-12s | %-16s | %-12s | %-12s%n", "Account ID", "Account Number", "Balance", "Customer ID");
                System.out.println("---------------------------------------------------------------");
                for (Bank_Accounts acc : accounts) {
                    System.out.printf("%-12d | %-16s | $%-11.2f | %-12d%n",
                            acc.getAccount_id(),
                            acc.getAccount_number(),
                            acc.getBalance(),
                            acc.getCustomer().getCustomer_id());
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // View all Transactions
    public void viewAllTransactions() {
        Session session = Config.getSession();
        try {
            session.beginTransaction();
            List<Transactions> transactions = session.createQuery("FROM Transactions", Transactions.class).getResultList();

            System.out.println("\n=== TRANSACTIONS TABLE ===");
            if (transactions.isEmpty()) {
                System.out.println("No transactions found.");
            } else {
                System.out.printf("%-15s | %-12s | %-15s | %-10s | %-20s%n",
                        "Transaction ID", "Account ID", "Type", "Amount", "Timestamp");
                System.out.println("-----------------------------------------------------------------------------------");
                for (Transactions tx : transactions) {
                    System.out.printf("%-15d | %-12d | %-15s | $%-9.2f | %-20s%n",
                            tx.getTransaction_id(),
                            tx.getBankAccount().getAccount_id(),
                            tx.getTransaction_type(),
                            tx.getAmount(),
                            tx.getTransaction_timestamp());
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}