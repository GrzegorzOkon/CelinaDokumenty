package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPALublinRepozytorium extends JPARepozytorium {
	private static JPALublinRepozytorium jpaLublinRepozytorium;
	
	private JPALublinRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Lublin_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPALublinRepozytorium pobierzInstancje() {
		if (jpaLublinRepozytorium == null) {
			jpaLublinRepozytorium = new JPALublinRepozytorium();
		} 
		
		return jpaLublinRepozytorium;
	}
}