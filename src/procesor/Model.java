package procesor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import kontroler.wejscie.Dokument;
import procesor.dao.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.entity.DokumentZCentralaDokumenty;
import procesor.dao.service.JPACelinaRepository;
import procesor.dao.sqlite.entity.WystawionaWersja;
import procesor.dao.sqlite.service.JPASQLiteRepozytorium;
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
	
	public void porównajWersje() {
		AktualnaWersja aktualnaWersja = KontrolerWersji.pobierzInstancjê().pobierzAktualn¹Wersjê();
		WystawionaWersja wystawionaWersja = null;
		
		try {
			wystawionaWersja = JPASQLiteRepozytorium.pobierzInstancjê().findInVersion();
		} catch (Exception ex) {
	
		}
		
		if (!aktualnaWersja.equals(wystawionaWersja)) {
			String tytu³ = "Komunikat o wersji...";
			String nag³ówek = "Pojawi³a siê aktualizacja oprogramowania.";
			String treœæ = "Na serwerze jest wystawiona aplikacja " + wystawionaWersja.getNazwa() + " v" + wystawionaWersja.getMajor() + "." + wystawionaWersja.getMinor() + "." + wystawionaWersja.getRelease() + " (rev. " + wystawionaWersja.getKompilacja() + ").\n" 
				+ "Posiadasz wersjê " + aktualnaWersja.getLokalnyMajor() + "." + aktualnaWersja.getLokalnyMinor() + "." + aktualnaWersja.getLokalnyRelease() + " (rev. " + aktualnaWersja.getLokalnaKomplilacja() + ").\n\n" 
				+ wystawionaWersja.getŒcie¿ka();
			
		}
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
	
	public List<String> generujRaporty(List<Dokument> dokumenty) {
		return GeneratorRaportów.pobierzInstancjê().utwórzRaport(dokumenty);
	}
	
	public void zapiszDoAnalizy(List<String> raporty) {
		String data = pobierzSpersonalizowan¹Datê();
		String u¿ytkownik = null;
		
		try {
			u¿ytkownik = System.getProperty("user.name"); 
		} catch(Exception ex) {
			return;
		}
		
		for (String raport : raporty) {
			JPASQLiteRepozytorium.pobierzInstancjê().insertInReports(data, u¿ytkownik, raport);
		}
	}
	
    /**
     * Bie¿¹ca data jest podana w formacie 15-07-2015, 14:17:45.
     */
    private static String pobierzSpersonalizowan¹Datê() {
        Date bie¿¹cyCzas = new Date(); 
        SimpleDateFormat spersonalizowanyFormatDaty = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
        String spersonalizowanaData = spersonalizowanyFormatDaty.format(bie¿¹cyCzas);
        
        return spersonalizowanaData;
    } 
}