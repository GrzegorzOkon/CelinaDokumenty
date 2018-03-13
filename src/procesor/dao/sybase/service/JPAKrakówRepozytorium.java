package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAKrak�wRepozytorium extends JPARepozytorium {
	private static JPAKrak�wRepozytorium jpaKrakowRepozytorium;
	
	private JPAKrak�wRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Krak�w_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAKrak�wRepozytorium pobierzInstancje() {
		if (jpaKrakowRepozytorium == null) {
			jpaKrakowRepozytorium = new JPAKrak�wRepozytorium();
		} 
		
		return jpaKrakowRepozytorium;
	}
}
