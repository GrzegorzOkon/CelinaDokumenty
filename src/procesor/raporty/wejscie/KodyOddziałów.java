package procesor.raporty.wejscie;

import java.util.*;

/**
 * Klasa wzorca projektowego Singleton umo�liwiaj�ca przes�anie kodu oddzia�u celnego i zwracaj�ca opis s�owny plac�wki wraz z izb�.
 * 
 * @author Grzegorz Oko�
 */
public class KodyOddzia��w {
	private static volatile KodyOddzia��w instancja;
	
	private Map<String, String> kodyOddzia��w;
	
	private KodyOddzia��w() {
		kodyOddzia��w = new HashMap<>();
		inicjalizujKody();
	}
	
	public static KodyOddzia��w pobierzInstancj�() {
		if (instancja == null) {
			synchronized(KodyOddzia��w.class) {
				if(instancja == null) {
					instancja = new KodyOddzia��w();
				}
			}
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
		
		kodyOddzia��w.put("351020", "Oddzia� Celny II w Krakowie, Izba Administracji Skarbowej w Krakowie");
		kodyOddzia��w.put("351030", "Oddzia� Celny Port Lotniczy Krak�w-Balice, Izba Administracji Skarbowej w Krakowie");
		kodyOddzia��w.put("351050", "Oddzia� Celny w Chy�nem, Izba Administracji Skarbowej w Krakowie");
		kodyOddzia��w.put("351060", "Oddzia� Celny w Andrychowie, Izba Administracji Skarbowej w Krakowie");
		kodyOddzia��w.put("353010", "Oddzia� Celny w Nowym S�czu, Izba Administracji Skarbowej w Krakowie");
		kodyOddzia��w.put("353030", "Oddzia� Celny w Tarnowie, Izba Administracji Skarbowej w Krakowie");
		
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
