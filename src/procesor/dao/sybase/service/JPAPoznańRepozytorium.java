package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAPoznañRepozytorium extends JPARepozytorium {
	private static JPAPoznañRepozytorium jpaPoznañRepozytorium;
	
	private JPAPoznañRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Poznañ_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAPoznañRepozytorium pobierzInstancje() {
		if (jpaPoznañRepozytorium == null) {
			jpaPoznañRepozytorium = new JPAPoznañRepozytorium();
		} 
		
		return jpaPoznañRepozytorium;
	}
}
