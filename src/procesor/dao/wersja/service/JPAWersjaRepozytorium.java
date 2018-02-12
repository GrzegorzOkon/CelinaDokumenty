package procesor.dao.wersja.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import procesor.dao.wersja.entity.WystawionaWersja;

public class JPAWersjaRepozytorium {
	private static final String SELECT_WYSTAWIONAWERSJA_IN_VERSION = "SELECT w FROM WystawionaWersja w";

	private EntityManagerFactory menedzerEncjiFabryka;
	private EntityManager menedzerEncji;
	
	public JPAWersjaRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("SQLite_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public WystawionaWersja findInVersion() throws NoResultException, Exception {
		WystawionaWersja wersja = menedzerEncji.createQuery(SELECT_WYSTAWIONAWERSJA_IN_VERSION, WystawionaWersja.class)
			.getSingleResult();
		
		return wersja;
	}
}