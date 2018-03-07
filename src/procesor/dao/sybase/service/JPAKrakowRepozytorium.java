package procesor.dao.sybase.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAKrakowRepozytorium {
	private static JPAKrakowRepozytorium jpaKrakowRepozytorium;
	
	private EntityManagerFactory menedzerEncjiFabryka;
	private EntityManager menedzerEncji;
	
	private JPAKrakowRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAKrakowRepozytorium pobierzInstancje() {
		if (jpaKrakowRepozytorium == null) {
			jpaKrakowRepozytorium = new JPAKrakowRepozytorium();
		} 
		
		return jpaKrakowRepozytorium;
	}
}
