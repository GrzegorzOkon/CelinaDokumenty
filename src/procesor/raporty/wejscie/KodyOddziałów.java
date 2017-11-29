package procesor.raporty.wejscie;

import java.util.*;

public class KodyOddzia³ów {
	private static KodyOddzia³ów instancja;
	
	private Map<String, String> kodyOddzia³ów;
	
	private KodyOddzia³ów() {
		kodyOddzia³ów = new HashMap<>();
		inicjalizujKody();
	}
	
	public static KodyOddzia³ów pobierzInstancjê() {
		if (instancja == null) {
			instancja = new KodyOddzia³ów();
		} 		
		
		return instancja;
	}
	
	public String pobierzKod(String kod) {
		return kodyOddzia³ów.get(kod);
	}
	
	private void inicjalizujKody() {
		kodyOddzia³ów.put("321010", "Oddzia³ Celny „Basen V” w Gdyni, Izba Administracji Skarbowej w Gdañsku");
		kodyOddzia³ów.put("321030", "Oddzia³ Celny „Baza Kontenerowa” w Gdyni, Izba Administracji Skarbowej w Gdañsku");
		kodyOddzia³ów.put("321050", "Oddzia³ Celny „Basen IV” w Gdyni, Izba Administracji Skarbowej w Gdañsku");
		kodyOddzia³ów.put("321070", "Oddzia³ Celny „Nabrze¿e Bu³garskie” w Gdyni, Izba Administracji Skarbowej w Gdañsku");
		kodyOddzia³ów.put("322010", "Oddzia³ Celny „Op³otki” w Gdañsku, Izba Administracji Skarbowej w Gdañsku");
		kodyOddzia³ów.put("322020", "Oddzia³ Celny „Nabrze¿e Wiœlane” w Gdañsku, Izba Administracji Skarbowej w Gdañsku");
		kodyOddzia³ów.put("322030", "Oddzia³ Celny „Basen im. W³adys³awa IV” w Gdañsku, Izba Administracji Skarbowej w Gdañsku");
		kodyOddzia³ów.put("322050", "Oddzia³ Celny Port Lotniczy Gdañsk-Rêbiechowo, Izba Administracji Skarbowej w Gdañsku");
		kodyOddzia³ów.put("322060", "Oddzia³ Celny w Tczewie, Izba Administracji Skarbowej w Gdañsku");
		kodyOddzia³ów.put("322070", "Oddzia³ Celny w Kwidzynie, Izba Administracji Skarbowej w Gdañsku");
		kodyOddzia³ów.put("322080", "Oddzia³ Celny „Terminal Kontenerowy” w Gdañsku, Izba Administracji Skarbowej w Gdañsku");
		kodyOddzia³ów.put("322090", "Oddzia³ Celny Pocztowy w Pruszczu Gdañskim, Izba Administracji Skarbowej w Gdañsku");
		kodyOddzia³ów.put("323010", "Oddzia³ Celny w S³upsku, Izba Administracji Skarbowej w Gdañsku");
		
		kodyOddzia³ów.put("441040", "Oddzia³ Celny IV w Warszawie, Izba Administracji Skarbowej w Warszawie");		
		kodyOddzia³ów.put("442020", "Oddzia³ Celny VI w Warszawie, Izba Administracji Skarbowej w Warszawie");	
		kodyOddzia³ów.put("443010", "Oddzia³ Celny Osobowy w Warszawie, Izba Administracji Skarbowej w Warszawie");
		kodyOddzia³ów.put("443020", "Oddzia³ Celny Towarowy I w Warszawie, Izba Administracji Skarbowej w Warszawie");
		kodyOddzia³ów.put("444010", "Oddzia³ Celny w Radomiu, Izba Administracji Skarbowej w Warszawie");
		kodyOddzia³ów.put("445010", "Oddzia³ Celny I w Pruszkowie, Izba Administracji Skarbowej w Warszawie");
		kodyOddzia³ów.put("446010", "Oddzia³ Celny w Siedlcach, Izba Administracji Skarbowej w Warszawie");
		kodyOddzia³ów.put("446020", "Oddzia³ Celny w Garwolinie, Izba Administracji Skarbowej w Warszawie");
		kodyOddzia³ów.put("447010", "Oddzia³ Celny w Ciechanowie, Izba Administracji Skarbowej w Warszawie");
	}
}
