package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAKatowiceRepozytorium extends JPARepozytorium {
	private static JPAKatowiceRepozytorium jpaKatowiceRepozytorium;
	
	private JPAKatowiceRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Katowice_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAKatowiceRepozytorium pobierzInstancje() {
		if (jpaKatowiceRepozytorium == null) {
			jpaKatowiceRepozytorium = new JPAKatowiceRepozytorium();
		} 
		
		return jpaKatowiceRepozytorium;
	}
}