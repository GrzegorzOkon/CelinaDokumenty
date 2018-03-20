package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAZielonaG�raRepozytorium extends JPARepozytorium {
	private static JPAZielonaG�raRepozytorium jpaZielonaG�raRepozytorium;
	
	private JPAZielonaG�raRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("ZielonaG�ra_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAZielonaG�raRepozytorium pobierzInstancje() {
		if (jpaZielonaG�raRepozytorium == null) {
			jpaZielonaG�raRepozytorium = new JPAZielonaG�raRepozytorium();
		} 
		
		return jpaZielonaG�raRepozytorium;
	}
}