package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAGda�skRepozytorium extends JPARepozytorium {
	private static JPAGda�skRepozytorium jpaGda�skRepozytorium;
	
	private JPAGda�skRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Gda�sk_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAGda�skRepozytorium pobierzInstancje() {
		if (jpaGda�skRepozytorium == null) {
			jpaGda�skRepozytorium = new JPAGda�skRepozytorium();
		} 
		
		return jpaGda�skRepozytorium;
	}
}