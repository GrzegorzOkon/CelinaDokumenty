package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAKrakówRepozytorium extends JPARepozytorium {
	private static JPAKrakówRepozytorium jpaKrakowRepozytorium;
	
	private JPAKrakówRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Kraków_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAKrakówRepozytorium pobierzInstancje() {
		if (jpaKrakowRepozytorium == null) {
			jpaKrakowRepozytorium = new JPAKrakówRepozytorium();
		} 
		
		return jpaKrakowRepozytorium;
	}
}
