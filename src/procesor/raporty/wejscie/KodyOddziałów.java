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
		
		kodyOddzia��w.put("341010", "Oddzia� Celny w Kielcach, Izba Administracji Skarbowej w Kielcach");
		kodyOddzia��w.put("341020", "Oddzia� Celny w Starachowicach, Izba Administracji Skarbowej w Kielcach");
		
		kodyOddzia��w.put("351020", "Oddzia� Celny II w Krakowie, Izba Administracji Skarbowej w Krakowie");
		kodyOddzia��w.put("351030", "Oddzia� Celny Port Lotniczy Krak�w-Balice, Izba Administracji Skarbowej w Krakowie");
		kodyOddzia��w.put("351050", "Oddzia� Celny w Chy�nem, Izba Administracji Skarbowej w Krakowie");
		kodyOddzia��w.put("351060", "Oddzia� Celny w Andrychowie, Izba Administracji Skarbowej w Krakowie");
		kodyOddzia��w.put("353010", "Oddzia� Celny w Nowym S�czu, Izba Administracji Skarbowej w Krakowie");
		kodyOddzia��w.put("353030", "Oddzia� Celny w Tarnowie, Izba Administracji Skarbowej w Krakowie");
		
		kodyOddzia��w.put("361010", "Oddzia� Celny I w �odzi, Izba Administracji Skarbowej w �odzi");
		kodyOddzia��w.put("361030", "Oddzia� Celny w Sieradzu, Izba Administracji Skarbowej w �odzi");
		kodyOddzia��w.put("362010", "Oddzia� Celny II w �odzi, Izba Administracji Skarbowej w �odzi");
		kodyOddzia��w.put("362030", "Oddzia� Celny w Kutnie, Izba Administracji Skarbowej w �odzi");
		kodyOddzia��w.put("363010", "Oddzia� Celny w Piotrkowie Trybunalskim, Izba Administracji Skarbowej w �odzi");
		
		kodyOddzia��w.put("371010", "Oddzia� Celny w Olsztynie, Izba Administracji Skarbowej w Olsztynie");		
		kodyOddzia��w.put("371020", "Oddzia� Celny w Korszach, Izba Administracji Skarbowej w Olsztynie");
		kodyOddzia��w.put("371030", "Oddzia� Celny w Bezledach, Izba Administracji Skarbowej w Olsztynie");		
		kodyOddzia��w.put("371050", "Oddzia� Celny w E�ku, Izba Administracji Skarbowej w Olsztynie");
		kodyOddzia��w.put("372010", "Oddzia� Celny w Elbl�gu, Izba Administracji Skarbowej w Olsztynie");		
		kodyOddzia��w.put("372020", "Oddzia� Celny w Braniewie, Izba Administracji Skarbowej w Olsztynie");
		kodyOddzia��w.put("372040", "Oddzia� Celny w I�awie, Izba Administracji Skarbowej w Olsztynie");		
		kodyOddzia��w.put("372050", "Oddzia� Celny w Grzechotkach, Izba Administracji Skarbowej w Olsztynie");
		
		kodyOddzia��w.put("381010", "Oddzia� Celny w Opolu, Izba Administracji Skarbowej w Opolu");
		kodyOddzia��w.put("381030", "Oddzia� Celny w K�dzierzynie-Ko�lu, Izba Administracji Skarbowej w Opolu");	
		kodyOddzia��w.put("381040", "Oddzia� Celny w Nysie, Izba Administracji Skarbowej w Opolu");
		
		kodyOddzia��w.put("391010", "Oddzia� Celny w Poznaniu, Izba Administracji Skarbowej w Poznaniu");		
		kodyOddzia��w.put("391020", "Oddzia� Celny �MTP� w Poznaniu, Izba Administracji Skarbowej w Poznaniu");	
		kodyOddzia��w.put("391030", "Oddzia� Celny Port Lotniczy Pozna�-�awica, Izba Administracji Skarbowej w Poznaniu");		
		kodyOddzia��w.put("391040", "Oddzia� Celny w G�dkach, Izba Administracji Skarbowej w Poznaniu");	
		kodyOddzia��w.put("392010", "Oddzia� Celny w Pile, Izba Administracji Skarbowej w Poznaniu");		
		kodyOddzia��w.put("393010", "Oddzia� Celny w Lesznie, Izba Administracji Skarbowej w Poznaniu");	
		kodyOddzia��w.put("393020", "Oddzia� Celny w Nowym Tomy�lu, Izba Administracji Skarbowej w Poznaniu");		
		kodyOddzia��w.put("394010", "Oddzia� Celny w Kaliszu, Izba Administracji Skarbowej w Poznaniu");	
		kodyOddzia��w.put("394020", "Oddzia� Celny w Koninie, Izba Administracji Skarbowej w Poznaniu");	
		
		kodyOddzia��w.put("401010", "Oddzia� Celny w Przemy�lu, Izba Administracji Skarbowej w Rzeszowie");		
		kodyOddzia��w.put("401030", "Oddzia� Celny w Medyce, Izba Administracji Skarbowej w Rzeszowie");	
		kodyOddzia��w.put("401040", "Oddzia� Celny Kolejowy Przemy�l-Medyka, Izba Administracji Skarbowej w Rzeszowie");		
		kodyOddzia��w.put("401060", "Oddzia� Celny w Korczowej, Izba Administracji Skarbowej w Rzeszowie");
		kodyOddzia��w.put("402010", "Oddzia� Celny w Rzeszowie, Izba Administracji Skarbowej w Rzeszowie");		
		kodyOddzia��w.put("402020", "Oddzia� Celny Port Lotniczy Rzesz�w-Jasionka, Izba Administracji Skarbowej w Rzeszowie");	
		kodyOddzia��w.put("402050", "Oddzia� Celny w Stalowej Woli, Izba Administracji Skarbowej w Rzeszowie");		
		kodyOddzia��w.put("402060", "Oddzia� Celny w Mielcu, Izba Administracji Skarbowej w Rzeszowie");	
		kodyOddzia��w.put("404010", "Oddzia� Celny w Kro�nie, Izba Administracji Skarbowej w Rzeszowie");	
		
		kodyOddzia��w.put("411010", "Oddzia� Celny w Zielonej G�rze, Izba Administracji Skarbowej w Zielonej G�rze");	
		kodyOddzia��w.put("411020", "Oddzia� Celny w Olszynie, Izba Administracji Skarbowej w Zielonej G�rze");		
		kodyOddzia��w.put("412010", "Oddzia� Celny w Gorzowie Wielkopolskim, Izba Administracji Skarbowej w Zielonej G�rze");	
		kodyOddzia��w.put("412020", "Oddzia� Celny w �wiecku, Izba Administracji Skarbowej w Zielonej G�rze");		
		
		kodyOddzia��w.put("421010", "Oddzia� Celny w Szczecinie, Izba Administracji Skarbowej w Szczecinie");		
		kodyOddzia��w.put("421030", "Oddzia� Celny �Nabrze�e �asztownia� w Szczecinie, Izba Administracji Skarbowej w Szczecinie");	
		kodyOddzia��w.put("421050", "Oddzia� Celny Port Lotniczy Szczecin-Goleni�w, Izba Administracji Skarbowej w Szczecinie");		
		kodyOddzia��w.put("421060", "Oddzia� Celny w Stargardzie, Izba Administracji Skarbowej w Szczecinie");
		kodyOddzia��w.put("421080", "Oddzia� Celny w �winouj�ciu, Izba Administracji Skarbowej w Szczecinie");		
		kodyOddzia��w.put("422010", "Oddzia� Celny w Koszalinie, Izba Administracji Skarbowej w Szczecinie");	
		kodyOddzia��w.put("422020", "Oddzia� Celny w Ko�obrzegu, Izba Administracji Skarbowej w Szczecinie");		
		kodyOddzia��w.put("422030", "Oddzia� Celny w Szczecinku, Izba Administracji Skarbowej w Szczecinie");
		
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
