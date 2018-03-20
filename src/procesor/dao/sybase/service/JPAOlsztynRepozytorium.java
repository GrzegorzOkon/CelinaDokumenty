package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAOlsztynRepozytorium extends JPARepozytorium {
	private static JPAOlsztynRepozytorium jpaOlsztynRepozytorium;
	
	private JPAOlsztynRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Olsztyn_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAOlsztynRepozytorium pobierzInstancje() {
		if (jpaOlsztynRepozytorium == null) {
			jpaOlsztynRepozytorium = new JPAOlsztynRepozytorium();
		} 
		
		return jpaOlsztynRepozytorium;
	}
}