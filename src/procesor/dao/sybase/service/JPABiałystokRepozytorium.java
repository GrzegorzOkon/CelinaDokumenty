package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPABia造stokRepozytorium extends JPARepozytorium {
	private static JPABia造stokRepozytorium jpaBia造stokRepozytorium;
	
	private JPABia造stokRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Bia造stok_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPABia造stokRepozytorium pobierzInstancje() {
		if (jpaBia造stokRepozytorium == null) {
			jpaBia造stokRepozytorium = new JPABia造stokRepozytorium();
		} 
		
		return jpaBia造stokRepozytorium;
	}
}