package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAKielceRepozytorium extends JPARepozytorium {
	private static JPAKielceRepozytorium jpaKielceRepozytorium;
	
	private JPAKielceRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Kielce_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAKielceRepozytorium pobierzInstancje() {
		if (jpaKielceRepozytorium == null) {
			jpaKielceRepozytorium = new JPAKielceRepozytorium();
		} 
		
		return jpaKielceRepozytorium;
	}
}