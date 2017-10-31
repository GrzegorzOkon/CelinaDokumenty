package procesor;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import procesor.dao.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.entity.DokumentZCentralaDokumenty;
import procesor.dao.service.JPACelinaRepository;

/**
 * Klasa modelu w architekturze mvc.
 * 
 * @author Grzegorz Okoñ
 */
public class Model {
	
	public List<DokumentZCentralaCntrValidDok> findByNrAktInCntrValidDok(String numerAkt) throws Exception {	
		List<DokumentZCentralaCntrValidDok> dok;
		
		try {
			dok = JPACelinaRepository.pobierzInstancje().findByNrAktInCntrValidDok(numerAkt);
	    } catch (Exception ex) {
	    	throw ex;
	    }
		
		return dok;
	}
	
	public DokumentZCentralaCntrValidDok findByIdDokInCntrValidDok(String idDok) throws Exception {	      
	      DokumentZCentralaCntrValidDok dok;
	      
	      try {
	    	  dok = JPACelinaRepository.pobierzInstancje().findByIdDokInCntrValidDok(idDok);
	      } catch (NoResultException ex) {
	    	  dok = null;
	      } catch (Exception ex) {
	    	  throw ex;
	      }

		 return dok;
	}
	
	public DokumentZCentralaDokumenty findByIdDokInDokumenty(String idDok) throws Exception {			      
	      DokumentZCentralaDokumenty dok;
	      
	      try {
	    	  dok = JPACelinaRepository.pobierzInstancje().findByIdDokInDokumenty(idDok);
	      } catch (NoResultException ex) {
	    	  dok = null;
	      } catch (Exception ex) {
	    	  throw ex;
	      }

		 return dok;
	}
	
	public DokumentZCentralaDokumenty findBySymDokInDokumenty(String symDok) throws Exception {			      
	      DokumentZCentralaDokumenty dok;
	      
	      try {
	    	  dok = JPACelinaRepository.pobierzInstancje().findBySymDokInDokumenty(symDok);
	      } catch (NoResultException ex) {
	    	  dok = null;
	      } catch (Exception ex) {
	    	  throw ex;
	      }

		 return dok;
	}
}