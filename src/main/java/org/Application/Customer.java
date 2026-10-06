package org.Application;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity 
public class Customer {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int customer_id;

	    private String customer_name;
	    
	    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    private List<Bank_Accounts> bankAccounts = new ArrayList<>();

	    public Customer() {
	    }

	    public Customer(String customer_name) {
	        this.customer_name = customer_name;
	    }

	    public int getCustomer_id() {
	        return customer_id;
	    }

	    public void setCustomer_id(int customer_id) {
	        this.customer_id = customer_id;
	    }

	    public String getCustomer_name() {
	        return customer_name;
	    }

	    public void setCustomer_name(String customer_name) {
	        this.customer_name = customer_name;
	    }
	    
	    public List<Bank_Accounts> getBankAccounts() {
	        return bankAccounts;
	    }
	    
	    public void setBankAccounts(List<Bank_Accounts> bankAccounts) {
	        this.bankAccounts = bankAccounts;
	    }
	    
}
