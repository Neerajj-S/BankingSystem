package org.Application;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity

public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transaction_id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false) 
    private Bank_Accounts bankAccount;

    private String transaction_type;

    private double amount;

    @CreationTimestamp 
    private LocalDateTime transaction_timestamp;

    public Transactions() {
    }

    public Transactions(Bank_Accounts bankAccount, String transaction_type, double amount) {        
        this.bankAccount = bankAccount;
        this.transaction_type = transaction_type;
        this.amount = amount;
        
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Bank_Accounts getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Bank_Accounts bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransaction_timestamp() {
        return transaction_timestamp;
    }

    public void setTransaction_timestamp(LocalDateTime transaction_timestamp) {
        this.transaction_timestamp = transaction_timestamp;
    }
}
