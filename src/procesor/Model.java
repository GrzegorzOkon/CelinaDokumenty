package procesor;

import java.util.List;

import javax.persistence.NoResultException;

import kontroler.wejscie.Dokument;
import procesor.dao.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.entity.DokumentZCentralaDokumenty;
import procesor.dao.service.JPACelinaRepository;
import procesor.dao.sqlite.entity.WystawionaWersja;
import procesor.dao.sqlite.service.JPASQLiteRepozytorium;
import procesor.raporty.GeneratorRaport�w;
import procesor.wersja.KontrolerWersji;
import procesor.wersja.wejscie.AktualnaWersja;
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
	
	public String pobierzOpis() {
		return KontrolerWersji.pobierzInstancj�().pobierzOpis();
	}
	
	public void por�wnajWersje() {
		AktualnaWersja aktualnaWersja = KontrolerWersji.pobierzInstancj�().pobierzAktualn�Wersj�();
		WystawionaWersja wystawionaWersja = null;
		
		try {
			wystawionaWersja = JPASQLiteRepozytorium.pobierzInstancj�().findInVersion();
		} catch (Exception ex) {
	
		}
		
		if (!aktualnaWersja.equals(wystawionaWersja)) {
			String tytu� = "Komunikat o wersji...";
			String nag��wek = "Pojawi�a si� aktualizacja oprogramowania.";
			String tre�� = "Na serwerze jest wystawiona aplikacja " + wystawionaWersja.getNazwa() + " v" + wystawionaWersja.getMajor() + "." + wystawionaWersja.getMinor() + "." + wystawionaWersja.getRelease() + " (rev. " + wystawionaWersja.getKompilacja() + ").\n" 
				+ "Posiadasz wersj� " + aktualnaWersja.getLokalnyMajor() + "." + aktualnaWersja.getLokalnyMinor() + "." + aktualnaWersja.getLokalnyRelease() + " (rev. " + aktualnaWersja.getLokalnaKomplilacja() + ").\n\n" 
				+ wystawionaWersja.get�cie�ka();
			
		}
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
	
	public List<String> generujRaporty(List<Dokument> dokumenty) {
		return GeneratorRaport�w.pobierzInstancj�().utw�rzRaport(dokumenty);
	}
	
	public void zapiszDoAnalizy(List<String> raporty) {
		JPASQLiteRepozytorium.pobierzInstancj�().insertInReports();
	}
}