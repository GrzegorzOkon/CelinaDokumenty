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
