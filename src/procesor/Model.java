package procesor;

import java.util.List;

import javax.persistence.NoResultException;

import kontroler.wejscie.Dokument;
import procesor.dao.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.entity.DokumentZCentralaDokumenty;
import procesor.dao.service.JPACelinaRepository;
import procesor.dao.wersja.entity.WystawionaWersja;
import procesor.dao.wersja.service.JPAWersjaRepozytorium;
import procesor.raporty.GeneratorRaportów;
import procesor.wersja.KontrolerWersji;
import procesor.wersja.wejscie.AktualnaWersja;
import widok.Widok;

/**
 * Klasa modelu w architekturze mvc.
 * 
 * @author Grzegorz Okoñ
 */
public class Model {
	private Widok widok;
	
	public Model (Widok widok) {
		this.widok = widok;
	}
	
	public String pobierzOpis() {
		return KontrolerWersji.pobierzInstancjê().pobierzOpis();
	}
	
	public boolean porównajWersje() {
		AktualnaWersja aktualnaWersja = KontrolerWersji.pobierzInstancjê().pobierzAktualn¹Wersjê();
		WystawionaWersja wersja = null;
		
		try {
			wersja = new JPAWersjaRepozytorium().findInVersion();
		} catch (Exception ex) {
	
		}
		
		return aktualnaWersja.equals(wersja);	
	}
	
	/**
	 * Zwraca listê dokumentów wyszukanych po numerze w³asnym w tabeli cntr_vaid_dok. 
	 * Gdy nie ma nic w liœcie wtedy jest zwracany null.
	 * 
	 * @param numerAkt - numer po którym nast¹pi wyszukiwanie.
	 * 
	 * @return lista dokumentów wyszukanych po numerze w³asnym w tabeli cntr_vaid_dok lub null w przypadku pustej listy.
	 * 
	 * @throws Exception - je¿eli zajdzie jakikolwiek wyj¹tek.
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
	
	public List<DokumentZCentralaDokumenty> findBySymDokInDokumenty(String symDok) throws Exception {			         
		List<DokumentZCentralaDokumenty> dokumentyZTabeliDokumenty;
		
		try {
			dokumentyZTabeliDokumenty = JPACelinaRepository.pobierzInstancje().findBySymDokInDokumenty(symDok);
			
			if (dokumentyZTabeliDokumenty.isEmpty() == true) {
				return null;
			}
	    } catch (Exception ex) {
	    	throw ex;
	    }
		
		return dokumentyZTabeliDokumenty;
	}
	
	public void generujRaporty(List<Dokument> dokumenty) {
		widok.wyœwietlRaporty(GeneratorRaportów.pobierzInstancjê().utwórzRaport(dokumenty)); ;
	}
}