package org.Application;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransferService {

    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount) {
        Session session = Config.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Fetch Source Account
            Bank_Accounts fromAccount = session.createQuery(
                    "FROM Bank_Accounts b WHERE b.account_number = :accNum", Bank_Accounts.class)
                    .setParameter("accNum", fromAccountNumber)
                    .uniqueResult();

            // Fetch Destination Account
            Bank_Accounts toAccount = session.createQuery(
                    "FROM Bank_Accounts b WHERE b.account_number = :accNum", Bank_Accounts.class)
                    .setParameter("accNum", toAccountNumber)
                    .uniqueResult();

            // Validation
            if (fromAccount == null) {
                System.out.println("Transfer failed: Source account does not exist.");
                return;
            }
            if (toAccount == null) {
                System.out.println("Transfer failed: Destination account does not exist.");
                return;
            }
            if (fromAccount.getBalance() < amount) {
                System.out.println("Transfer failed: Insufficient balance in source account.");
                return;
            }

            // Perform Balance Updates
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);

            session.merge(fromAccount);
            session.merge(toAccount);

            // Create Transaction records
            Transactions outTx = new Transactions(fromAccount, "TRANSFER_OUT", amount);
            Transactions inTx = new Transactions(toAccount, "TRANSFER_IN", amount);

            session.persist(outTx);
            session.persist(inTx);

            tx.commit();
            System.out.println("Transfer of $" + amount + " from " + fromAccountNumber + " to " + toAccountNumber + " completed successfully!");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error during transfer: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}