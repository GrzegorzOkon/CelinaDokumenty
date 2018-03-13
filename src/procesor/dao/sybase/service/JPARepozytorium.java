package procesor.dao.sybase.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import procesor.dao.sybase.entity.DokumentZIzbyDokumenty;

public abstract class JPARepozytorium {
	private static final String SELECT_DOKUMENT_BY_IDDOK_IN_DOKUMENTY = "SELECT d FROM DokumentZIzbyDokumenty d WHERE d.identyfikatorDokumentu = :idDok";
	private static final String SELECT_DOKUMENT_BY_SYMDOK_IN_DOKUMENTY = "SELECT d FROM DokumentZIzbyDokumenty d WHERE d.symbolDokumentu = :symDok";	

	EntityManagerFactory menedzerEncjiFabryka;
	EntityManager menedzerEncji;
	
	public DokumentZIzbyDokumenty findByIdDokInDokumenty(String idDok) throws NoResultException, Exception {
		DokumentZIzbyDokumenty dokument = menedzerEncji.createQuery(SELECT_DOKUMENT_BY_IDDOK_IN_DOKUMENTY, DokumentZIzbyDokumenty.class)
				.setParameter("idDok", idDok)
				.getSingleResult();
		
		return dokument;
	}
	
	public List<DokumentZIzbyDokumenty> findBySymDokInDokumenty(String symDok) throws Exception {
		List<DokumentZIzbyDokumenty> dokumenty = menedzerEncji.createQuery(SELECT_DOKUMENT_BY_SYMDOK_IN_DOKUMENTY, DokumentZIzbyDokumenty.class)
				.setParameter("symDok", symDok)
				.getResultList();
		
		return dokumenty;
	}
}
