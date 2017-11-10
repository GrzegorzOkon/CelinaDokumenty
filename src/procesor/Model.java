package procesor;

import java.util.List;

import javax.persistence.NoResultException;

import kontroler.wejscie.Dokument;
import procesor.dao.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.entity.DokumentZCentralaDokumenty;
import procesor.dao.service.JPACelinaRepository;
import procesor.raporty.GeneratorRaport�w;
import widok.Widok;

/**
 * Klasa modelu w architekturze mvc.
 * 
 * @author Grzegorz Oko�
 */
public class Model {
	private Widok widok;
	
	public Model (Widok widok) {
		this.widok = widok;
	}
	
	/**
	 * Zwraca list� dokument�w wyszukanych po numerze w�asnym w tabeli cntr_vaid_dok. 
	 * Gdy nie ma nic w li�cie wtedy jest zwracany null.
	 * 
	 * @param numerAkt - numer po kt�rym nast�pi wyszukiwanie.
	 * 
	 * @return lista dokument�w wyszukanych po numerze w�asnym w tabeli cntr_vaid_dok lub null w przypadku pustej listy.
	 * 
	 * @throws Exception - je�eli zajdzie jakikolwiek wyj�tek.
	 */
	public List<DokumentZCentralaCntrValidDok> findByNrAktInCntrValidDok(String numerAkt) throws Exception {
		List<DokumentZCentralaCntrValidDok> dokumentyZTabeliCntrValidDok;
		
		try {
			dokumentyZTabeliCntrValidDok = JPACelinaRepository.pobierzInstancje().findByNrAktInCntrValidDok(numerAkt);
			
			if (dokumentyZTabeliCntrValidDok.isEmpty() == true) {
				return null;
			}
	    } catch (Exception ex) {
	    	throw ex;
	    }
		
		return dokumentyZTabeliCntrValidDok;
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
	
	public void generujRaporty(List<Dokument> dokumenty) {
		widok.wy�wietlRaporty(GeneratorRaport�w.pobierzInstancj�().utw�rzRaport(dokumenty)); ;
	}
}