package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPABydgoszczRepozytorium extends JPARepozytorium {
	private static JPABydgoszczRepozytorium jpaBydgoszczRepozytorium;
	
	private JPABydgoszczRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Bydgoszcz_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPABydgoszczRepozytorium pobierzInstancje() {
		if (jpaBydgoszczRepozytorium == null) {
			jpaBydgoszczRepozytorium = new JPABydgoszczRepozytorium();
		} 
		
		return jpaBydgoszczRepozytorium;
	}
}