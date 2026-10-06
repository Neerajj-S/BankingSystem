package org.Application;

import org.hibernate.Session;

public class Main {

	public static void main(String[] args) {
		

	        Session session = Config.getSession();

	        session.beginTransaction();
	        
	        Customer cu = new Customer();
            cu.setCustomer_name("Tom");
            session.persist(cu);

           
            Bank_Accounts bk = new Bank_Accounts(cu, "ACC100300400", 15000.00);
            session.persist(bk);

    
            Transactions tx = new Transactions(bk, "DEPOSIT", 1000.00);
            session.persist(tx);

	        session.getTransaction().commit();

	        session.close();
	    
	}
}
