package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPABia�ystokRepozytorium extends JPARepozytorium {
	private static JPABia�ystokRepozytorium jpaBia�ystokRepozytorium;
	
	private JPABia�ystokRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Bia�ystok_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPABia�ystokRepozytorium pobierzInstancje() {
		if (jpaBia�ystokRepozytorium == null) {
			jpaBia�ystokRepozytorium = new JPABia�ystokRepozytorium();
		} 
		
		return jpaBia�ystokRepozytorium;
	}
}