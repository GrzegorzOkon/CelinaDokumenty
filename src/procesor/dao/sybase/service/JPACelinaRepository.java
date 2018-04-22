package procesor.dao.sybase.service;

import java.util.List;

import javax.persistence.*;

import procesor.dao.sybase.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.sybase.entity.DokumentZCentralaDokumenty;
import procesor.zdarzenia.RejestratorZdarzeñ;

public class JPACelinaRepository {
	private static final String SELECT_DOKUMENT_BY_NRAKT_IN_CNTRVALIDDOK = "SELECT d FROM DokumentZCentralaCntrValidDok d WHERE d.numerWlasny = :numerAkt";
	private static final String SELECT_DOKUMENT_BY_IDDOK_IN_CNTRVALIDDOK = "SELECT d FROM DokumentZCentralaCntrValidDok d WHERE d.identyfikatorDokumentu = :idDok";
	private static final String SELECT_DOKUMENT_BY_IDDOK_IN_DOKUMENTY = "SELECT d FROM DokumentZCentralaDokumenty d WHERE d.identyfikatorDokumentu = :idDok";
	private static final String SELECT_DOKUMENT_BY_SYMDOK_IN_DOKUMENTY = "SELECT d FROM DokumentZCentralaDokumenty d WHERE d.symbolDokumentu = :symDok";	
	
	private static JPACelinaRepository jpaCelinaRepository;
	
	private EntityManagerFactory menedzerEncjiFabryka;
	private EntityManager menedzerEncji;
	
	private JPACelinaRepository() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Centrala_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
		
		if (menedzerEncji.isOpen())
			RejestratorZdarzeñ.pobierzInstancjê().info("Próba po³¹czenia z baz¹ danych " + menedzerEncji.getProperties().get("javax.persistence.jdbc.url").toString().substring(menedzerEncji.getProperties().get("javax.persistence.jdbc.url").toString().indexOf(":") + 1)
												   	   + " poprzez " + menedzerEncji.getProperties().get("javax.persistence.jdbc.url").toString().substring(0, menedzerEncji.getProperties().get("javax.persistence.jdbc.url").toString().indexOf(":")));
	}
	
	public static JPACelinaRepository pobierzInstancje() {
		if (jpaCelinaRepository == null) {
			jpaCelinaRepository = new JPACelinaRepository();
		} 
		
		return jpaCelinaRepository;
	}
	
	public List<DokumentZCentralaCntrValidDok> findByNrAktInCntrValidDok(String numerAkt) throws Exception {
		List<DokumentZCentralaCntrValidDok> dokumenty = menedzerEncji.createQuery(SELECT_DOKUMENT_BY_NRAKT_IN_CNTRVALIDDOK, DokumentZCentralaCntrValidDok.class)
				.setParameter("numerAkt", numerAkt)
				.getResultList();
		
		return dokumenty;
	}
	
	public DokumentZCentralaCntrValidDok findByIdDokInCntrValidDok(String idDok) throws NoResultException, Exception {
		DokumentZCentralaCntrValidDok dokument = menedzerEncji.createQuery(SELECT_DOKUMENT_BY_IDDOK_IN_CNTRVALIDDOK, DokumentZCentralaCntrValidDok.class)
				.setParameter("idDok", idDok)
				.getSingleResult();
		
		return dokument;
	}
	
	public DokumentZCentralaDokumenty findByIdDokInDokumenty(String idDok) throws NoResultException, Exception {
		DokumentZCentralaDokumenty dokument = menedzerEncji.createQuery(SELECT_DOKUMENT_BY_IDDOK_IN_DOKUMENTY, DokumentZCentralaDokumenty.class)
				.setParameter("idDok", idDok)
				.getSingleResult();
		
		return dokument;
	}
	
	public List<DokumentZCentralaDokumenty> findBySymDokInDokumenty(String symDok) throws Exception {
		List<DokumentZCentralaDokumenty> dokumenty = menedzerEncji.createQuery(SELECT_DOKUMENT_BY_SYMDOK_IN_DOKUMENTY, DokumentZCentralaDokumenty.class)
				.setParameter("symDok", symDok)
				.getResultList();
		
		return dokumenty;
	}
}
