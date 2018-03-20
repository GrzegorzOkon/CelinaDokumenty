package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAPozna�Repozytorium extends JPARepozytorium {
	private static JPAPozna�Repozytorium jpaPozna�Repozytorium;
	
	private JPAPozna�Repozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Pozna�_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAPozna�Repozytorium pobierzInstancje() {
		if (jpaPozna�Repozytorium == null) {
			jpaPozna�Repozytorium = new JPAPozna�Repozytorium();
		} 
		
		return jpaPozna�Repozytorium;
	}
}
