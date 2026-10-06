package org.Application;

import java.util.List;
import org.hibernate.Session;

public class TransactionHistory {

    public void viewTransactionHistory(String accountNumber) {
        Session session = Config.getSession();

        try {
            session.beginTransaction();

            // Fetch Bank Account
            Bank_Accounts account = session.createQuery(
                    "FROM Bank_Accounts b WHERE b.account_number = :accNum", Bank_Accounts.class)
                    .setParameter("accNum", accountNumber)
                    .uniqueResult();

            if (account != null) {
                System.out.println("\n=== TRANSACTION HISTORY ===");
                System.out.println("Account Number : " + account.getAccount_number());
                System.out.println("Current Balance: $" + account.getBalance());
                System.out.println("Customer       : " + account.getCustomer().getCustomer_name());
                System.out.println("----------------------------------------");

                // Get transactions via HQL or list relationship
                List<Transactions> transactions = session.createQuery(
                        "FROM Transactions t WHERE t.bankAccount.account_id = :accId", Transactions.class)
                        .setParameter("accId", account.getAccount_id())
                        .getResultList();

                if (transactions.isEmpty()) {
                    System.out.println("No transactions recorded for this account.");
                } else {
                    for (Transactions tx : transactions) {
                        System.out.println("Tx ID     : " + tx.getTransaction_id());
                        System.out.println("Type      : " + tx.getTransaction_type());
                        System.out.println("Amount    : $" + tx.getAmount());
                        System.out.println("Timestamp : " + tx.getTransaction_timestamp());
                        System.out.println("----------------------------------------");
                    }
                }
            } else {
                System.out.println("Account with number " + accountNumber + " not found.");
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