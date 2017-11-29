package procesor.raporty.wejscie;

import java.util.*;

public class KodyOddzia��w {
	private static KodyOddzia��w instancja;
	
	private Map<String, String> kodyOddzia��w;
	
	private KodyOddzia��w() {
		kodyOddzia��w = new HashMap<>();
		inicjalizujKody();
	}
	
	public static KodyOddzia��w pobierzInstancj�() {
		if (instancja == null) {
			instancja = new KodyOddzia��w();
		} 		
		
		return instancja;
	}
	
	public String pobierzKod(String kod) {
		return kodyOddzia��w.get(kod);
	}
	
	private void inicjalizujKody() {
		kodyOddzia��w.put("321010", "Oddzia� Celny �Basen V� w Gdyni, Izba Administracji Skarbowej w Gda�sku");
		kodyOddzia��w.put("321030", "Oddzia� Celny �Baza Kontenerowa� w Gdyni, Izba Administracji Skarbowej w Gda�sku");
		kodyOddzia��w.put("321050", "Oddzia� Celny �Basen IV� w Gdyni, Izba Administracji Skarbowej w Gda�sku");
		kodyOddzia��w.put("321070", "Oddzia� Celny �Nabrze�e Bu�garskie� w Gdyni, Izba Administracji Skarbowej w Gda�sku");
		kodyOddzia��w.put("322010", "Oddzia� Celny �Op�otki� w Gda�sku, Izba Administracji Skarbowej w Gda�sku");
		kodyOddzia��w.put("322020", "Oddzia� Celny �Nabrze�e Wi�lane� w Gda�sku, Izba Administracji Skarbowej w Gda�sku");
		kodyOddzia��w.put("322030", "Oddzia� Celny �Basen im. W�adys�awa IV� w Gda�sku, Izba Administracji Skarbowej w Gda�sku");
		kodyOddzia��w.put("322050", "Oddzia� Celny Port Lotniczy Gda�sk-R�biechowo, Izba Administracji Skarbowej w Gda�sku");
		kodyOddzia��w.put("322060", "Oddzia� Celny w Tczewie, Izba Administracji Skarbowej w Gda�sku");
		kodyOddzia��w.put("322070", "Oddzia� Celny w Kwidzynie, Izba Administracji Skarbowej w Gda�sku");
		kodyOddzia��w.put("322080", "Oddzia� Celny �Terminal Kontenerowy� w Gda�sku, Izba Administracji Skarbowej w Gda�sku");
		kodyOddzia��w.put("322090", "Oddzia� Celny Pocztowy w Pruszczu Gda�skim, Izba Administracji Skarbowej w Gda�sku");
		kodyOddzia��w.put("323010", "Oddzia� Celny w S�upsku, Izba Administracji Skarbowej w Gda�sku");
		
		kodyOddzia��w.put("441040", "Oddzia� Celny IV w Warszawie, Izba Administracji Skarbowej w Warszawie");		
		kodyOddzia��w.put("442020", "Oddzia� Celny VI w Warszawie, Izba Administracji Skarbowej w Warszawie");	
		kodyOddzia��w.put("443010", "Oddzia� Celny Osobowy w Warszawie, Izba Administracji Skarbowej w Warszawie");
		kodyOddzia��w.put("443020", "Oddzia� Celny Towarowy I w Warszawie, Izba Administracji Skarbowej w Warszawie");
		kodyOddzia��w.put("444010", "Oddzia� Celny w Radomiu, Izba Administracji Skarbowej w Warszawie");
		kodyOddzia��w.put("445010", "Oddzia� Celny I w Pruszkowie, Izba Administracji Skarbowej w Warszawie");
		kodyOddzia��w.put("446010", "Oddzia� Celny w Siedlcach, Izba Administracji Skarbowej w Warszawie");
		kodyOddzia��w.put("446020", "Oddzia� Celny w Garwolinie, Izba Administracji Skarbowej w Warszawie");
		kodyOddzia��w.put("447010", "Oddzia� Celny w Ciechanowie, Izba Administracji Skarbowej w Warszawie");
	}
}
