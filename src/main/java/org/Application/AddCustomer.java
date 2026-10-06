package org.Application;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddCustomer {

    public void addnewCustomer(String customerName, String accountNumber, double initialBalance) {
        Session session = Config.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // 1. Create and persist Customer
            Customer customer = new Customer(customerName);
            session.persist(customer);

            // 2. Create and persist Bank Account linked to Customer
            Bank_Accounts account = new Bank_Accounts(customer, accountNumber, initialBalance);
            session.persist(account);

            tx.commit();
            System.out.println("Customer and Bank Account created successfully!");
            System.out.println("Assigned Customer ID: " + customer.getCustomer_id());

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error adding customer: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}