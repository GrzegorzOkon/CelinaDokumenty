package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPASzczecinRepozytorium extends JPARepozytorium {
	private static JPASzczecinRepozytorium jpaSzczecinRepozytorium;
	
	private JPASzczecinRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Szczecin_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPASzczecinRepozytorium pobierzInstancje() {
		if (jpaSzczecinRepozytorium == null) {
			jpaSzczecinRepozytorium = new JPASzczecinRepozytorium();
		} 
		
		return jpaSzczecinRepozytorium;
	}
}