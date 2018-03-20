package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAWarszawaCUDORepozytorium extends JPARepozytorium {
	private static JPAWarszawaCUDORepozytorium jpaWarszawaCUDORepozytorium;
	
	private JPAWarszawaCUDORepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Warszawa_CUDO_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAWarszawaCUDORepozytorium pobierzInstancje() {
		if (jpaWarszawaCUDORepozytorium == null) {
			jpaWarszawaCUDORepozytorium = new JPAWarszawaCUDORepozytorium();
		} 
		
		return jpaWarszawaCUDORepozytorium;
	}
}
