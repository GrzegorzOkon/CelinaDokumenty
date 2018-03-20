package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAGdañskRepozytorium extends JPARepozytorium {
	private static JPAGdañskRepozytorium jpaGdañskRepozytorium;
	
	private JPAGdañskRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Gdañsk_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAGdañskRepozytorium pobierzInstancje() {
		if (jpaGdañskRepozytorium == null) {
			jpaGdañskRepozytorium = new JPAGdañskRepozytorium();
		} 
		
		return jpaGdañskRepozytorium;
	}
}