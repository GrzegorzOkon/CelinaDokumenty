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
	
	public void insertInReports() {
		/*menedzerEncji.getTransaction( ).begin( );
		
		procesor.dao.wersja.entity.Raport raport = new procesor.dao.wersja.entity.Raport();
		raport.setIdentyfikator(2553);
		raport.setData("17-02-2018, 11:06:05");
		raport.setU¿ytkownik("okongrzegorz");
		raport.setRaport("TEST");
		
		menedzerEncji.persist(raport);
		menedzerEncji.getTransaction().commit();*/
		
	    /*Query query = entityManager.createNativeQuery("INSERT INTO topic (ID, TITLE,CREATION_DATE) " +
	            " VALUES(?,?,?)");
	        query.setParameter(1, id);
	        query.setParameter(2, title);
	        query.setParameter(3, creationDate);
	        query.executeUpdate();
	    }*/
		
	    /*EntityManagerFactory factory = Persistence
	            .createEntityManagerFactory("SQLite_JPA");
	    EntityManager  entityManager = factory.createEntityManager();
	    entityManager.getTransaction().begin();*/

	    /*em.createNativeQuery("INSERT INTO emp (EMP_ID, name) " +
	    	    "       VALUES(?, 'asdf')")
	    	      .setParameter(1, id)
	    	      .executeUpdate();*/
		
		menedzerEncji.getTransaction( ).begin( );
		
		/*procesor.dao.wersja.entity.Raport raport = new procesor.dao.wersja.entity.Raport();
		raport.setIdentyfikator(2553);
		raport.setData("18-02-2018, 11:06:05");
		raport.setU¿ytkownik("okongrzegorz");
		raport.setRaport("TEST");*/
		
		Query query = menedzerEncji.createNativeQuery("INSERT INTO raports (date, user, raport) VALUES(?, ?, ?)");
		query.setParameter(1, "18-02-2018, 11:06:05");
		query.setParameter(2, "okongrzegorz");
		query.setParameter(3, "TEST2");
		query.executeUpdate();
		
		//menedzerEncji.close( );
		//menedzerEncji.close( );
		menedzerEncji.getTransaction().commit();
	    /*entityManager.getTransaction().commit();
	    entityManager.close();
	    factory.close();*/
	}
	
	/*String query = "insert into Employee values(1,?)";

	em.createNativeQuery(query)
	   .setParameter(1, "Tom")
	   .executeUpdate();*/
	
	/*Query query = em.createQuery("INSERT INTO TestDataEntity (NAME, VALUE) VALUES (:name, :value)");
	query.setParameter("name", name);
	query.setParameter("value", value);
	query.executeUpdate();*/
	
    /*EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
    
    EntityManager entitymanager = emfactory.createEntityManager( );
    entitymanager.getTransaction( ).begin( );

    Employee employee = new Employee( ); 
    employee.setEid( 1201 );
    employee.setEname( "Gopal" );
    employee.setSalary( 40000 );
    employee.setDeg( "Technical Manager" );
    
    entitymanager.persist( employee );
    entitymanager.getTransaction( ).commit( );

    entitymanager.close( );
    emfactory.close( );*/
}