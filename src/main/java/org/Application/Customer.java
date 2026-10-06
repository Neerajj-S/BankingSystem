package org.Application;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Customer {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int customer_id;

	    private String customer_name;

	    // Default constructor
	    public Customer() {
	    }

	    // Parameterized constructor
	    public Customer(String customer_name) {
	        this.customer_name = customer_name;
	    }

	    // Getters and setters
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
	    
}
