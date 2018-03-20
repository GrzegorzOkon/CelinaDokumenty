package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAWarszawaRepozytorium extends JPARepozytorium {
	private static JPAWarszawaRepozytorium jpaWarszawaRepozytorium;
	
	private JPAWarszawaRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Warszawa_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAWarszawaRepozytorium pobierzInstancje() {
		if (jpaWarszawaRepozytorium == null) {
			jpaWarszawaRepozytorium = new JPAWarszawaRepozytorium();
		} 
		
		return jpaWarszawaRepozytorium;
	}
}
