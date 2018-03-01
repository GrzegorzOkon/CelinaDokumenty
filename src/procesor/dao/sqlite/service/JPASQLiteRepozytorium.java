package procesor.dao.sqlite.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import procesor.dao.sqlite.entity.WystawionaWersja;

public class JPASQLiteRepozytorium {
	private static final String SELECT_WYSTAWIONAWERSJA_IN_VERSION = "SELECT w FROM WystawionaWersja w";
	
	//Polecenie zapisane w native query bazy danych
	private static final String INSERT_RAPORT_IN_RAPORTS = "INSERT INTO raports (date, user, raport) VALUES(?, ?, ?)";

	private static JPASQLiteRepozytorium jpaSqliteRepozytorium;
	
	private EntityManagerFactory menedzerEncjiFabryka;
	private EntityManager menedzerEncji;
	
	public JPASQLiteRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("SQLite_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPASQLiteRepozytorium pobierzInstancjê() {
		if (jpaSqliteRepozytorium == null) {
			jpaSqliteRepozytorium = new JPASQLiteRepozytorium();
		} 
		
		return jpaSqliteRepozytorium;
	}
	
	public WystawionaWersja findInVersion() throws NoResultException, Exception {
		WystawionaWersja wersja = menedzerEncji.createQuery(SELECT_WYSTAWIONAWERSJA_IN_VERSION, WystawionaWersja.class)
			.getSingleResult();
		
		return wersja;
	}
	
	public void rozpocznijTransakcjê() {
		menedzerEncji.getTransaction().begin();
	}
	
	public void zamknijTransakcjê() {
		menedzerEncji.getTransaction().commit();
	}
	
	public void insertInReports(String data, String u¿ytkownik, String raport) {		
		Query query = menedzerEncji.createNativeQuery("INSERT INTO raports (date, user, raport) VALUES(?, ?, ?)");
		query.setParameter(1, data);
		query.setParameter(2, u¿ytkownik);
		query.setParameter(3, "RAPORT: " + raport);
		query.executeUpdate();
	}
}