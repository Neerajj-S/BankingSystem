package org.Application;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity 

public class Bank_Accounts {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int account_id;

	    @ManyToOne 
	    @JoinColumn(name = "customer_id", nullable = false)
	    private Customer customer;

	    private String account_number;

	    private double balance;
	    
	    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    private List<Transactions> transactions = new ArrayList<>();

	    public Bank_Accounts() {
	    }

	    public Bank_Accounts(Customer customer, String account_number, double balance) {
	        this.customer = customer;
	        this.account_number = account_number;
	        this.balance = balance;
	    }

	    public int getAccount_id() {
	        return account_id;
	    }

	    public void setAccount_id(int account_id) {
	        this.account_id = account_id;
	    }

	    public Customer getCustomer() {
	        return customer;
	    }

	    public void setCustomer(Customer customer) {
	        this.customer = customer;
	    }

	    public String getAccount_number() {
	        return account_number;
	    }

	    public void setAccount_number(String account_number) {
	        this.account_number = account_number;
	    }

	    public double getBalance() {
	        return balance;
	    }

	    public void setBalance(double balance) {
	        this.balance = balance;
	    }
	    
	    public List<Transactions> getTransactions() {
	        return transactions;
	    }

	    public void setTransactions(List<Transactions> transactions) {
	        this.transactions = transactions;
	    }
	
}
