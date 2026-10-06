package org.Application;

import org.hibernate.Session;

public class TransferService {

	public static void main(String[] args) {
		

        Session session = Config.getSession();

        session.beginTransaction();
        Customer cu = session.get(Customer.class, 3);
        
        Bank_Accounts bk = new Bank_Accounts(cu, "ACC100400500", 10000.00);
        session.persist(bk);
        
        Transactions tx = new Transactions(bk, "DEPOSIT", 2000.00);
        session.persist(tx);
        
        session.getTransaction().commit();

        session.close();
	}
}
