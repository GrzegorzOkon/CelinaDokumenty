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
		kodyOddzia��w.put("301010", "Oddzia� Celny w Bia�ej Podlaskiej, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia��w.put("301020", "Oddzia� Celny w Ma�aszewiczach, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia��w.put("301040", "Oddzia� Celny w Koroszczynie, Oddzia� Celny w Ma�aszewiczach, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia��w.put("302010", "Oddzia� Celny w Lublinie, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia��w.put("302020", "Oddzia� Celny w Pu�awach, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia��w.put("302040", "Oddzia� Celny w Che�mie, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia��w.put("302050", "Oddzia� Celny w Dorohusku, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia��w.put("302060", "Oddzia� Celny Drogowy w Dorohusku, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia��w.put("302070", "Oddzia� Celny Port Lotniczy Lublin, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia��w.put("303010", "Oddzia� Celny w Zamo�ciu, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia��w.put("303020", "Oddzia� Celny w Hrebennem, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia��w.put("303030", "Oddzia� Celny w Hrubieszowie, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia��w.put("303080", "Oddzia� Celny w Tomaszowie Lubelskim, Izba Administracji Skarbowej w Lublinie");
		
		kodyOddzia��w.put("311010", "Oddzia� Celny w Bia�ymstoku, Izba Administracji Skarbowej w Bia�ymstoku");
		kodyOddzia��w.put("311020", "Oddzia� Celny Kolejowy w Ku�nicy, Izba Administracji Skarbowej w Bia�ymstoku");
		kodyOddzia��w.put("311030", "Oddzia� Celny Drogowy w Ku�nicy, Izba Administracji Skarbowej w Bia�ymstoku");
		kodyOddzia��w.put("311050", "Oddzia� Celny w Siemian�wce, Izba Administracji Skarbowej w Bia�ymstoku");
		kodyOddzia��w.put("311070", "Oddzia� Celny w Bobrownikach, Izba Administracji Skarbowej w Bia�ymstoku");
		kodyOddzia��w.put("312010", "Oddzia� Celny w �om�y, Izba Administracji Skarbowej w Bia�ymstoku");
		kodyOddzia��w.put("313010", "Oddzia� Celny w Suwa�kach, Izba Administracji Skarbowej w Bia�ymstoku");
		
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
		
		kodyOddzia��w.put("331010", "Oddzia� Celny w Chorzowie, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia��w.put("331020", "Oddzia� Celny w Tychach, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia��w.put("331030", "Oddzia� Celny w S�awkowie, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia��w.put("331040", "Oddzia� Celny Towarowy Port Lotniczy Katowice-Pyrzowice, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia��w.put("331050", "Oddzia� Celny Osobowy Port Lotniczy Katowice-Pyrzowice, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia��w.put("332010", "Oddzia� Celny w Gliwicach, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia��w.put("332030", "Oddzia� Celny w Raciborzu, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia��w.put("332040", "Oddzia� Celny Pocztowy w Zabrzu, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia��w.put("333010", "Oddzia� Celny w Cz�stochowie, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia��w.put("335010", "Oddzia� Celny w Czechowicach-Dziedzicach, Izba Administracji Skarbowej w Katowicach");
		
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
