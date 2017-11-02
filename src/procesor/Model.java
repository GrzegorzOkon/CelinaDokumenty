package procesor;

import java.util.List;

import javax.persistence.NoResultException;

import procesor.dao.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.entity.DokumentZCentralaDokumenty;
import procesor.dao.service.JPACelinaRepository;

/**
 * Klasa modelu w architekturze mvc.
 * 
 * @author Grzegorz Oko�
 */
public class Model {
	/**
	 * Zwraca list� dokument�w wyszukanych po numerze w�asnym w tabeli cntr_vaid_dok.
	 * 
	 * @param numerAkt - numer po kt�rym nast�pi wyszukiwanie.
	 * 
	 * @return lista dokument�w wyszukanych po numerze w�asnym w tabeli cntr_vaid_dok
	 * 
	 * @throws Exception - je�eli zajdzie wyj�tek inny ni� NoResultException.
	 */
	public List<DokumentZCentralaCntrValidDok> findByNrAktInCntrValidDok(String numerAkt) throws Exception {		
		try {
			return JPACelinaRepository.pobierzInstancje().findByNrAktInCntrValidDok(numerAkt);
	    } catch (Exception ex) {
	    	throw ex;
	    }
	}
	
	public DokumentZCentralaCntrValidDok findByIdDokInCntrValidDok(String idDok) throws Exception {	            
		try {
			return JPACelinaRepository.pobierzInstancje().findByIdDokInCntrValidDok(idDok);
		} catch (NoResultException ex) {
			return null;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	public DokumentZCentralaDokumenty findByIdDokInDokumenty(String idDok) throws Exception {			          
		try {
			return JPACelinaRepository.pobierzInstancje().findByIdDokInDokumenty(idDok);
	    } catch (NoResultException ex) {
	    	return null;
	    } catch (Exception ex) {
	    	throw ex;
	    }
	}
	
	public DokumentZCentralaDokumenty findBySymDokInDokumenty(String symDok) throws Exception {			         
		try {
			return JPACelinaRepository.pobierzInstancje().findBySymDokInDokumenty(symDok);
	    } catch (NoResultException ex) {
	    	return null;
	    } catch (Exception ex) {
	    	throw ex;
	    }
	}
}