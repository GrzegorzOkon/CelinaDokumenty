package procesor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import kontroler.wejscie.Dokument;
import procesor.dao.sqlite.entity.WystawionaWersja;
import procesor.dao.sqlite.service.JPASQLiteRepozytorium;
import procesor.dao.sybase.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.sybase.entity.DokumentZCentralaDokumenty;
import procesor.dao.sybase.entity.DokumentZIzbyDokumenty;
import procesor.dao.sybase.service.JPACelinaRepository;
import procesor.dao.sybase.service.JPAKrak�wRepozytorium;
import procesor.dao.sybase.service.JPARepozytorium;
import procesor.raporty.GeneratorRaport�w;
import procesor.raporty.wejscie.KodyOddzia��w;
import procesor.raporty.wejscie.Raport;
import procesor.wersja.KontrolerWersji;
import procesor.wersja.wejscie.AktualnaWersja;
import widok.Widok;

/**
 * Klasa modelu w architekturze mvc.
 * 
 * @author Grzegorz Oko�
 */
/**
 * @author Grzesiek
 *
 */
public class Model {
	private Widok widok;

	public Model(Widok widok) {
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
			String tre�� = "Na serwerze jest wystawiona aplikacja " + wystawionaWersja.getNazwa() + " v"
					+ wystawionaWersja.getMajor() + "." + wystawionaWersja.getMinor() + "."
					+ wystawionaWersja.getRelease() + " (rev. " + wystawionaWersja.getKompilacja() + ").\n"
					+ "Posiadasz wersj� " + aktualnaWersja.getLokalnyMajor() + "." + aktualnaWersja.getLokalnyMinor()
					+ "." + aktualnaWersja.getLokalnyRelease() + " (rev. " + aktualnaWersja.getLokalnaKomplilacja()
					+ ").\n\n" + wystawionaWersja.get�cie�ka();

		}
	}

	/**
	 * Zwraca list� dokument�w wyszukanych po numerze w�asnym w tabeli
	 * cntr_vaid_dok. Gdy nie ma nic w li�cie wtedy jest zwracany null.
	 * 
	 * @param numerAkt
	 *            - numer po kt�rym nast�pi wyszukiwanie.
	 * 
	 * @return lista dokument�w wyszukanych po numerze w�asnym w tabeli
	 *         cntr_vaid_dok lub null w przypadku pustej listy.
	 * 
	 * @throws Exception
	 *             - je�eli zajdzie jakikolwiek wyj�tek.
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

	public DokumentZIzbyDokumenty findByIdDokInDokumenty(String identyfikatorDokumentu, String identyfikatorJednostki) throws NullPointerException, Exception {	
		try {
			return pobierzRepozytorium(identyfikatorJednostki).findByIdDokInDokumenty(identyfikatorDokumentu);
		} catch (NoResultException ex) {
			return null;
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

	private JPARepozytorium pobierzRepozytorium(String identyfikatorJednostki) throws NullPointerException {
		JPARepozytorium repozytorium = null;
		String oddzia� = KodyOddzia��w.pobierzInstancj�().pobierzKod(identyfikatorJednostki);
		
		switch(oddzia�.substring(oddzia�.indexOf(",") + 2)) {
			case "Izba Administracji Skarbowej w Lublinie" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Bia�ymstoku" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Gda�sku" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Katowicach" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Kielcach" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Krakowie" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w �odzi" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Olsztynie" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Opolu" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Poznaniu" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Rzeszowie" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Zielonej G�rze" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Szczecinie" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Bydgoszczy" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Warszawie" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej we Wroc�awiu" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
		}
		
		return repozytorium;
	}
	
	public List<Raport> generujRaporty(List<Dokument> dokumenty) {
		return GeneratorRaport�w.pobierzInstancj�().utw�rzRaport(dokumenty);
	}

	public void zapiszDoAnalizy(List<Raport> raporty) {
		String data = pobierzSpersonalizowan�Dat�();
		String u�ytkownik = null;

		try {
			u�ytkownik = System.getProperty("user.name");
		} catch (Exception ex) {
			return;
		}

		JPASQLiteRepozytorium.pobierzInstancj�().rozpocznijTransakcj�();
		
		for (Raport raport : raporty) {
			JPASQLiteRepozytorium.pobierzInstancj�().insertInReports(data, u�ytkownik, raport.getRaportDlaHelpDesku());
		}
		
		JPASQLiteRepozytorium.pobierzInstancj�().zamknijTransakcj�();
	}

	/**
	 * Bie��ca data jest podana w formacie 15-07-2015, 14:17:45.
	 */
	private static String pobierzSpersonalizowan�Dat�() {
		Date bie��cyCzas = new Date();
		SimpleDateFormat spersonalizowanyFormatDaty = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
		String spersonalizowanaData = spersonalizowanyFormatDaty.format(bie��cyCzas);

		return spersonalizowanaData;
	}
}