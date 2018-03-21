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
import procesor.dao.sybase.service.JPABia³ystokRepozytorium;
import procesor.dao.sybase.service.JPABydgoszczRepozytorium;
import procesor.dao.sybase.service.JPACelinaRepository;
import procesor.dao.sybase.service.JPAGdañskRepozytorium;
import procesor.dao.sybase.service.JPAKatowiceRepozytorium;
import procesor.dao.sybase.service.JPAKielceRepozytorium;
import procesor.dao.sybase.service.JPAKrakówRepozytorium;
import procesor.dao.sybase.service.JPALublinRepozytorium;
import procesor.dao.sybase.service.JPAOlsztynRepozytorium;
import procesor.dao.sybase.service.JPAOpoleRepozytorium;
import procesor.dao.sybase.service.JPAPoznañRepozytorium;
import procesor.dao.sybase.service.JPARepozytorium;
import procesor.dao.sybase.service.JPARzeszówRepozytorium;
import procesor.dao.sybase.service.JPASzczecinRepozytorium;
import procesor.dao.sybase.service.JPAWarszawaCUDORepozytorium;
import procesor.dao.sybase.service.JPAWarszawaRepozytorium;
import procesor.dao.sybase.service.JPAWroc³awRepozytorium;
import procesor.dao.sybase.service.JPAZielonaGóraRepozytorium;
import procesor.dao.sybase.service.JPA£ódŸRepozytorium;
import procesor.raporty.GeneratorRaportów;
import procesor.raporty.wejscie.KodyOddzia³ów;
import procesor.raporty.wejscie.Raport;
import procesor.wersja.KontrolerWersji;
import procesor.wersja.wejscie.AktualnaWersja;
import widok.Widok;

/**
 * Klasa modelu w architekturze mvc.
 * 
 * @author Grzegorz Okoñ
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
			String treœæ = "Na serwerze jest wystawiona aplikacja " + wystawionaWersja.getNazwa() + " v"
					+ wystawionaWersja.getMajor() + "." + wystawionaWersja.getMinor() + "."
					+ wystawionaWersja.getRelease() + " (rev. " + wystawionaWersja.getKompilacja() + ").\n"
					+ "Posiadasz wersjê " + aktualnaWersja.getLokalnyMajor() + "." + aktualnaWersja.getLokalnyMinor()
					+ "." + aktualnaWersja.getLokalnyRelease() + " (rev. " + aktualnaWersja.getLokalnaKomplilacja()
					+ ").\n\n" + wystawionaWersja.getŒcie¿ka();

		}
	}

	/**
	 * Zwraca listê dokumentów wyszukanych po numerze w³asnym w tabeli
	 * cntr_vaid_dok. Gdy nie ma nic w liœcie wtedy jest zwracany null.
	 * 
	 * @param numerAkt
	 *            - numer po którym nast¹pi wyszukiwanie.
	 * 
	 * @return lista dokumentów wyszukanych po numerze w³asnym w tabeli
	 *         cntr_vaid_dok lub null w przypadku pustej listy.
	 * 
	 * @throws Exception
	 *             - je¿eli zajdzie jakikolwiek wyj¹tek.
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
	 * @param identyfikatorDokumentu - numer identyfikacyjny (w bazie kolumna id_dok) po którym przeprowadzane jest wyszukiwanie.
	 * 
	 * @param identyfikatorJednostki - kod oddzia³u po którym wybierane jest okreœlone po³aczenie do bazy lokalnej.
	 * 
	 * @return DokumentZIzbyDokumenty - dokument z tabeli dokumenty w bazie lokalnej.
	 * 
	 * @throws NullPointerException - w przypadku braku przes³anego kodu w klasie KodyOddzia³ów. 
	 * 
	 * @throws Exception - gdy nie mo¿na uzyskaæ po³aczenia do bazy.
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
	 * Zwraca repozytorium wybrane po przes³anym kodzie oddzia³u.
	 * W przypadku braku oddzia³u w klasie KodyOddzia³ów zwracany jest wyj¹tek.
	 * 
	 * @param identyfikatorJednostki - kod oddzia³u po którym wybierane jest po³aczenie do bazy lokalnej.
	 * 
	 * @return JPARepozytorium - klasa abstrakcyjna po której dziedzicz¹ wszystkie repozytoria lokalne.
	 * 
	 * @throws NullPointerException - w przypadku braku przes³anego kodu w klasie KodyOddzia³ów. 
	 */
	private JPARepozytorium pobierzRepozytorium(String identyfikatorJednostki) throws NullPointerException {
		JPARepozytorium repozytorium = null;
		String oddzia³ = KodyOddzia³ów.pobierzInstancjê().pobierzKod(identyfikatorJednostki);
		
		switch(oddzia³.substring(oddzia³.indexOf(",") + 2)) {
			case "Izba Administracji Skarbowej w Lublinie" : 
				repozytorium = JPALublinRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Bia³ymstoku" : 
				repozytorium = JPABia³ystokRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Gdañsku" : 
				repozytorium = JPAGdañskRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Katowicach" : 
				repozytorium = JPAKatowiceRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Kielcach" : 
				repozytorium = JPAKielceRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Krakowie" : 
				repozytorium = JPAKrakówRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w £odzi" : 
				repozytorium = JPA£ódŸRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Olsztynie" : 
				repozytorium = JPAOlsztynRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Opolu" : 
				repozytorium = JPAOpoleRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Poznaniu" : 
				repozytorium = JPAPoznañRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Rzeszowie" : 
				repozytorium = JPARzeszówRepozytorium.pobierzInstancje();
				break;
			case "Izba Administracji Skarbowej w Zielonej Górze" : 
				repozytorium = JPAZielonaGóraRepozytorium.pobierzInstancje();
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
			case "Izba Administracji Skarbowej we Wroc³awiu" : 
				repozytorium = JPAWroc³awRepozytorium.pobierzInstancje();
				break;
		}
		
		return repozytorium;
	}
	
	public List<Raport> generujRaporty(List<Dokument> dokumenty) {
		return GeneratorRaportów.pobierzInstancjê().utwórzRaport(dokumenty);
	}

	public void zapiszDoAnalizy(List<Raport> raporty) {
		String data = pobierzSpersonalizowan¹Datê();
		String u¿ytkownik = null;

		try {
			u¿ytkownik = System.getProperty("user.name");
		} catch (Exception ex) {
			return;
		}

		JPASQLiteRepozytorium.pobierzInstancjê().rozpocznijTransakcjê();
		
		for (Raport raport : raporty) {
			JPASQLiteRepozytorium.pobierzInstancjê().insertInReports(data, u¿ytkownik, raport.getRaportDlaHelpDesku());
		}
		
		JPASQLiteRepozytorium.pobierzInstancjê().zamknijTransakcjê();
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