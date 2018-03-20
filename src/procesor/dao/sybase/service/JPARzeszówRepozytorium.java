package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPARzesz�wRepozytorium extends JPARepozytorium {
	private static JPARzesz�wRepozytorium jpaRzesz�wRepozytorium;
	
	private JPARzesz�wRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Rzesz�w_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPARzesz�wRepozytorium pobierzInstancje() {
		if (jpaRzesz�wRepozytorium == null) {
			jpaRzesz�wRepozytorium = new JPARzesz�wRepozytorium();
		} 
		
		return jpaRzesz�wRepozytorium;
	}
}