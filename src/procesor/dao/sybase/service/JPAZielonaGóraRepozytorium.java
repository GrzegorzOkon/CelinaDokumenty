package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAZielonaGóraRepozytorium extends JPARepozytorium {
	private static JPAZielonaGóraRepozytorium jpaZielonaGóraRepozytorium;
	
	private JPAZielonaGóraRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("ZielonaGóra_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAZielonaGóraRepozytorium pobierzInstancje() {
		if (jpaZielonaGóraRepozytorium == null) {
			jpaZielonaGóraRepozytorium = new JPAZielonaGóraRepozytorium();
		} 
		
		return jpaZielonaGóraRepozytorium;
	}
}