package org.Application;

import org.hibernate.Session;

public class AddCustomer {

	public static void main(String[] args) {
		

        Session session = Config.getSession();

        session.beginTransaction();
        
        Customer cu = new Customer();
        cu.setCustomer_name("Jake");
        session.persist(cu);
        
        session.getTransaction().commit();

        session.close();
	}
}
