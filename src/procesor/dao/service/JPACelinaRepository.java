package procesor.dao.service;

//import java.util.List;

import javax.persistence.*;

import procesor.dao.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.entity.DokumentZCentralaDokumenty;

public class JPACelinaRepository {
	private static JPACelinaRepository jpaCelinaRepository;
	private EntityManagerFactory menedzerEncjiFabryka;
	private EntityManager menedzerEncji;
	
	private JPACelinaRepository() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPACelinaRepository pobierzInstancje() {
		if (jpaCelinaRepository == null) {
			jpaCelinaRepository = new JPACelinaRepository();
		} 
		
		return jpaCelinaRepository;
	}
	
	public DokumentZCentralaCntrValidDok findByIdDokInCntrValidDok(String idDok) throws NoResultException, Exception {
		return menedzerEncji.createQuery("SELECT d FROM DokumentZCentralaCntrValidDok d "
				+ "WHERE d.identyfikatorDokumentu = '" + idDok +"'", DokumentZCentralaCntrValidDok.class)
				.getSingleResult();
	}
	
	public DokumentZCentralaDokumenty findByIdDokInDokumenty(String idDok) throws NoResultException, Exception {
		return menedzerEncji.createQuery("SELECT d FROM DokumentZCentralaDokumenty d "
				+ "WHERE d.identyfikatorDokumentu = '" + idDok +"'", DokumentZCentralaDokumenty.class)
				.getSingleResult();
	}
}
