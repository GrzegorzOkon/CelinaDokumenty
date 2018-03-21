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
import procesor.dao.sybase.service.JPABia�ystokRepozytorium;
import procesor.dao.sybase.service.JPABydgoszczRepozytorium;
import procesor.dao.sybase.service.JPACelinaRepository;
import procesor.dao.sybase.service.JPAGda�skRepozytorium;
import procesor.dao.sybase.service.JPAKatowiceRepozytorium;
import procesor.dao.sybase.service.JPAKielceRepozytorium;
import procesor.dao.sybase.service.JPAKrak�wRepozytorium;
import procesor.dao.sybase.service.JPALublinRepozytorium;
import procesor.dao.sybase.service.JPAOlsztynRepozytorium;
import procesor.dao.sybase.service.JPAOpoleRepozytorium;
import procesor.dao.sybase.service.JPAPozna�Repozytorium;
import procesor.dao.sybase.service.JPARepozytorium;
import procesor.dao.sybase.service.JPARzesz�wRepozytorium;
import procesor.dao.sybase.service.JPASzczecinRepozytorium;
import procesor.dao.sybase.service.JPAWarszawaCUDORepozytorium;
import procesor.dao.sybase.service.JPAWarszawaRepozytorium;
import procesor.dao.sybase.service.JPAWroc�awRepozytorium;
import procesor.dao.sybase.service.JPAZielonaG�raRepozytorium;
import procesor.dao.sybase.service.JPA��d�Repozytorium;
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

	/**
	 * Zwraca dokument z lokalnej tabeli dokumenty.
	 * 
	 * @param identyfikatorDokumentu - numer identyfikacyjny (w bazie kolumna id_dok) po kt�rym przeprowadzane jest wyszukiwanie.
	 * 
	 * @param identyfikatorJednostki - kod oddzia�u po kt�rym wybierane jest okre�lone po�aczenie do bazy lokalnej.
	 * 
	 * @return DokumentZIzbyDokumenty - dokument z tabeli dokumenty w bazie lokalnej.
	 * 
	 * @throws NullPointerException - w przypadku braku przes�anego kodu w klasie KodyOddzia��w. 
	 * 
	 * @throws Exception - gdy nie mo�na uzyska� po�aczenia do bazy.
	 */
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

	/**
	 * Zwraca repozytorium wybrane po przes�anym kodzie oddzia�u.
	 * W przypadku braku oddzia�u w klasie KodyOddzia��w zwracany jest wyj�tek.
	 * 
	 * @param identyfikatorJednostki - kod oddzia�u po kt�rym wybierane jest po�aczenie do bazy lokalnej.
	 * 
	 * @return JPARepozytorium - klasa abstrakcyjna po kt�rej dziedzicz� wszystkie repozytoria lokalne.
	 * 
	 * @throws NullPointerException - w przypadku braku przes�anego kodu w klasie KodyOddzia��w. 
	 */
	private JPARepozytorium pobierzRepozytorium(String identyfikatorJednostki) throws NullPointerException {
		JPARepozytorium repozytorium = null;
		String oddzia� = KodyOddzia��w.pobierzInstancj�().pobierzKod(identyfikatorJednostki);
		
		switch(oddzia�.substring(oddzia�.indexOf(",") + 2)) {
			case "Izba Administracji Skarbowej w Lublinie" : 
				repozytorium = JPALublinRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Bia�ymstoku" : 
				repozytorium = JPABia�ystokRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Gda�sku" : 
				repozytorium = JPAGda�skRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Katowicach" : 
				repozytorium = JPAKatowiceRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Kielcach" : 
				repozytorium = JPAKielceRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Krakowie" : 
				repozytorium = JPAKrak�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w �odzi" : 
				repozytorium = JPA��d�Repozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Olsztynie" : 
				repozytorium = JPAOlsztynRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Opolu" : 
				repozytorium = JPAOpoleRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Poznaniu" : 
				repozytorium = JPAPozna�Repozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Rzeszowie" : 
				repozytorium = JPARzesz�wRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Zielonej G�rze" : 
				repozytorium = JPAZielonaG�raRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Szczecinie" : 
				repozytorium = JPASzczecinRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Bydgoszczy" : 
				repozytorium = JPABydgoszczRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Warszawie" : 
				if (identyfikatorJednostki.equals("443020")) {
					repozytorium = JPAWarszawaCUDORepozytorium.pobierzInstancje();
				} else {
					repozytorium = JPAWarszawaRepozytorium.pobierzInstancje();
				};
				break;
			case "Izba Administracji Skarbowej we Wroc�awiu" : 
				repozytorium = JPAWroc�awRepozytorium.pobierzInstancje();
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