package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPARzeszówRepozytorium extends JPARepozytorium {
	private static JPARzeszówRepozytorium jpaRzeszówRepozytorium;
	
	private JPARzeszówRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Rzeszów_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPARzeszówRepozytorium pobierzInstancje() {
		if (jpaRzeszówRepozytorium == null) {
			jpaRzeszówRepozytorium = new JPARzeszówRepozytorium();
		} 
		
		return jpaRzeszówRepozytorium;
	}
}