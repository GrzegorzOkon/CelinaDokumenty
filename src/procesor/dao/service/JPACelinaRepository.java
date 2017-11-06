package procesor.dao.service;

import java.util.List;

import javax.persistence.*;

import procesor.dao.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.entity.DokumentZCentralaDokumenty;

public class JPACelinaRepository {
	private static JPACelinaRepository jpaCelinaRepository;
	
	private static final String SELECT_DOKUMENT_BY_NRAKT_IN_CNTRVALIDDOK = "SELECT d FROM DokumentZCentralaCntrValidDok d WHERE d.numerWlasny = :numerAkt";
	private static final String SELECT_DOKUMENT_BY_IDDOK_IN_CNTRVALIDDOK = "SELECT d FROM DokumentZCentralaCntrValidDok d WHERE d.identyfikatorDokumentu = :idDok";
	private static final String SELECT_DOKUMENT_BY_IDDOK_IN_DOKUMENTY = "SELECT d FROM DokumentZCentralaDokumenty d WHERE d.identyfikatorDokumentu = :idDok";
	private static final String SELECT_DOKUMENT_BY_SYMDOK_IN_DOKUMENTY = "SELECT d FROM DokumentZCentralaDokumenty d WHERE d.symbolDokumentu = :symDok";	
	
	private EntityManagerFactory menedzerEncjiFabryka;
	private EntityManager menedzerEncji;
	
	private JPACelinaRepository() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory( "MySQL_JPA" );
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPACelinaRepository pobierzInstancje() {
		if (jpaCelinaRepository == null) {
			jpaCelinaRepository = new JPACelinaRepository();
		} 
		
		return jpaCelinaRepository;
	}
	
	public List<DokumentZCentralaCntrValidDok> findByNrAktInCntrValidDok(String numerAkt) throws Exception {
		return menedzerEncji.createQuery(SELECT_DOKUMENT_BY_NRAKT_IN_CNTRVALIDDOK, DokumentZCentralaCntrValidDok.class)
				.setParameter("numerAkt", numerAkt)
				.getResultList();
	}
	
	public DokumentZCentralaCntrValidDok findByIdDokInCntrValidDok(String idDok) throws NoResultException, Exception {
		return menedzerEncji.createQuery(SELECT_DOKUMENT_BY_IDDOK_IN_CNTRVALIDDOK, DokumentZCentralaCntrValidDok.class)
				.setParameter("idDok", idDok)
				.getSingleResult();
	}
	
	public DokumentZCentralaDokumenty findByIdDokInDokumenty(String idDok) throws NoResultException, Exception {
		return menedzerEncji.createQuery(SELECT_DOKUMENT_BY_IDDOK_IN_DOKUMENTY, DokumentZCentralaDokumenty.class)
				.setParameter("idDok", idDok)
				.getSingleResult();
	}
	
	public DokumentZCentralaDokumenty findBySymDokInDokumenty(String symDok) throws NoResultException, Exception {
		return menedzerEncji.createQuery(SELECT_DOKUMENT_BY_SYMDOK_IN_DOKUMENTY, DokumentZCentralaDokumenty.class)
				.setParameter("symDok", symDok)
				.getSingleResult();
	}
}