package procesor.raporty.wejscie;

import java.util.*;

/**
 * Klasa wzorca projektowego Singleton umo¿liwiaj¹ca przes³anie kodu oddzia³u celnego i zwracaj¹ca opis s³owny placówki wraz z izb¹.
 * 
 * @author Grzegorz Okoñ
 */
public class KodyOddzia³ów {
	private static volatile KodyOddzia³ów instancja;
	
	private Map<String, String> kodyOddzia³ów;
	
	private KodyOddzia³ów() {
		kodyOddzia³ów = new HashMap<>();
		inicjalizujKody();
	}
	
	public static KodyOddzia³ów pobierzInstancjê() {
		if (instancja == null) {
			synchronized(KodyOddzia³ów.class) {
				if(instancja == null) {
					instancja = new KodyOddzia³ów();
				}
			}
		} 		
		
		return instancja;
	}
	
	public String pobierzKod(String kod) {
		return kodyOddzia³ów.get(kod);
	}
	
	private void inicjalizujKody() {	
		kodyOddzia³ów.put("301010", "Oddzia³ Celny w Bia³ej Podlaskiej, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia³ów.put("301020", "Oddzia³ Celny w Ma³aszewiczach, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia³ów.put("301040", "Oddzia³ Celny w Koroszczynie, Oddzia³ Celny w Ma³aszewiczach, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia³ów.put("302010", "Oddzia³ Celny w Lublinie, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia³ów.put("302020", "Oddzia³ Celny w Pu³awach, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia³ów.put("302040", "Oddzia³ Celny w Che³mie, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia³ów.put("302050", "Oddzia³ Celny w Dorohusku, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia³ów.put("302060", "Oddzia³ Celny Drogowy w Dorohusku, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia³ów.put("302070", "Oddzia³ Celny Port Lotniczy Lublin, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia³ów.put("303010", "Oddzia³ Celny w Zamoœciu, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia³ów.put("303020", "Oddzia³ Celny w Hrebennem, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia³ów.put("303030", "Oddzia³ Celny w Hrubieszowie, Izba Administracji Skarbowej w Lublinie");
		kodyOddzia³ów.put("303080", "Oddzia³ Celny w Tomaszowie Lubelskim, Izba Administracji Skarbowej w Lublinie");
		
		kodyOddzia³ów.put("311010", "Oddzia³ Celny w Bia³ymstoku, Izba Administracji Skarbowej w Bia³ymstoku");
		kodyOddzia³ów.put("311020", "Oddzia³ Celny Kolejowy w KuŸnicy, Izba Administracji Skarbowej w Bia³ymstoku");
		kodyOddzia³ów.put("311030", "Oddzia³ Celny Drogowy w KuŸnicy, Izba Administracji Skarbowej w Bia³ymstoku");
		kodyOddzia³ów.put("311050", "Oddzia³ Celny w Siemianówce, Izba Administracji Skarbowej w Bia³ymstoku");
		kodyOddzia³ów.put("311070", "Oddzia³ Celny w Bobrownikach, Izba Administracji Skarbowej w Bia³ymstoku");
		kodyOddzia³ów.put("312010", "Oddzia³ Celny w £om¿y, Izba Administracji Skarbowej w Bia³ymstoku");
		kodyOddzia³ów.put("313010", "Oddzia³ Celny w Suwa³kach, Izba Administracji Skarbowej w Bia³ymstoku");
		
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
		
		kodyOddzia³ów.put("331010", "Oddzia³ Celny w Chorzowie, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia³ów.put("331020", "Oddzia³ Celny w Tychach, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia³ów.put("331030", "Oddzia³ Celny w S³awkowie, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia³ów.put("331040", "Oddzia³ Celny Towarowy Port Lotniczy Katowice-Pyrzowice, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia³ów.put("331050", "Oddzia³ Celny Osobowy Port Lotniczy Katowice-Pyrzowice, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia³ów.put("332010", "Oddzia³ Celny w Gliwicach, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia³ów.put("332030", "Oddzia³ Celny w Raciborzu, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia³ów.put("332040", "Oddzia³ Celny Pocztowy w Zabrzu, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia³ów.put("333010", "Oddzia³ Celny w Czêstochowie, Izba Administracji Skarbowej w Katowicach");
		kodyOddzia³ów.put("335010", "Oddzia³ Celny w Czechowicach-Dziedzicach, Izba Administracji Skarbowej w Katowicach");
		
		kodyOddzia³ów.put("341010", "Oddzia³ Celny w Kielcach, Izba Administracji Skarbowej w Kielcach");
		kodyOddzia³ów.put("341020", "Oddzia³ Celny w Starachowicach, Izba Administracji Skarbowej w Kielcach");
		
		kodyOddzia³ów.put("351020", "Oddzia³ Celny II w Krakowie, Izba Administracji Skarbowej w Krakowie");
		kodyOddzia³ów.put("351030", "Oddzia³ Celny Port Lotniczy Kraków-Balice, Izba Administracji Skarbowej w Krakowie");
		kodyOddzia³ów.put("351050", "Oddzia³ Celny w Chy¿nem, Izba Administracji Skarbowej w Krakowie");
		kodyOddzia³ów.put("351060", "Oddzia³ Celny w Andrychowie, Izba Administracji Skarbowej w Krakowie");
		kodyOddzia³ów.put("353010", "Oddzia³ Celny w Nowym S¹czu, Izba Administracji Skarbowej w Krakowie");
		kodyOddzia³ów.put("353030", "Oddzia³ Celny w Tarnowie, Izba Administracji Skarbowej w Krakowie");
		
		kodyOddzia³ów.put("361010", "Oddzia³ Celny I w £odzi, Izba Administracji Skarbowej w £odzi");
		kodyOddzia³ów.put("361030", "Oddzia³ Celny w Sieradzu, Izba Administracji Skarbowej w £odzi");
		kodyOddzia³ów.put("362010", "Oddzia³ Celny II w £odzi, Izba Administracji Skarbowej w £odzi");
		kodyOddzia³ów.put("362030", "Oddzia³ Celny w Kutnie, Izba Administracji Skarbowej w £odzi");
		kodyOddzia³ów.put("363010", "Oddzia³ Celny w Piotrkowie Trybunalskim, Izba Administracji Skarbowej w £odzi");
		
		kodyOddzia³ów.put("371010", "Oddzia³ Celny w Olsztynie, Izba Administracji Skarbowej w Olsztynie");		
		kodyOddzia³ów.put("371020", "Oddzia³ Celny w Korszach, Izba Administracji Skarbowej w Olsztynie");
		kodyOddzia³ów.put("371030", "Oddzia³ Celny w Bezledach, Izba Administracji Skarbowej w Olsztynie");		
		kodyOddzia³ów.put("371050", "Oddzia³ Celny w E³ku, Izba Administracji Skarbowej w Olsztynie");
		kodyOddzia³ów.put("372010", "Oddzia³ Celny w Elbl¹gu, Izba Administracji Skarbowej w Olsztynie");		
		kodyOddzia³ów.put("372020", "Oddzia³ Celny w Braniewie, Izba Administracji Skarbowej w Olsztynie");
		kodyOddzia³ów.put("372040", "Oddzia³ Celny w I³awie, Izba Administracji Skarbowej w Olsztynie");		
		kodyOddzia³ów.put("372050", "Oddzia³ Celny w Grzechotkach, Izba Administracji Skarbowej w Olsztynie");
		
		kodyOddzia³ów.put("381010", "Oddzia³ Celny w Opolu, Izba Administracji Skarbowej w Opolu");
		kodyOddzia³ów.put("381030", "Oddzia³ Celny w Kêdzierzynie-KoŸlu, Izba Administracji Skarbowej w Opolu");	
		kodyOddzia³ów.put("381040", "Oddzia³ Celny w Nysie, Izba Administracji Skarbowej w Opolu");
		
		kodyOddzia³ów.put("391010", "Oddzia³ Celny w Poznaniu, Izba Administracji Skarbowej w Poznaniu");		
		kodyOddzia³ów.put("391020", "Oddzia³ Celny „MTP” w Poznaniu, Izba Administracji Skarbowej w Poznaniu");	
		kodyOddzia³ów.put("391030", "Oddzia³ Celny Port Lotniczy Poznañ-£awica, Izba Administracji Skarbowej w Poznaniu");		
		kodyOddzia³ów.put("391040", "Oddzia³ Celny w G¹dkach, Izba Administracji Skarbowej w Poznaniu");	
		kodyOddzia³ów.put("392010", "Oddzia³ Celny w Pile, Izba Administracji Skarbowej w Poznaniu");		
		kodyOddzia³ów.put("393010", "Oddzia³ Celny w Lesznie, Izba Administracji Skarbowej w Poznaniu");	
		kodyOddzia³ów.put("393020", "Oddzia³ Celny w Nowym Tomyœlu, Izba Administracji Skarbowej w Poznaniu");		
		kodyOddzia³ów.put("394010", "Oddzia³ Celny w Kaliszu, Izba Administracji Skarbowej w Poznaniu");	
		kodyOddzia³ów.put("394020", "Oddzia³ Celny w Koninie, Izba Administracji Skarbowej w Poznaniu");	
		
		kodyOddzia³ów.put("401010", "Oddzia³ Celny w Przemyœlu, Izba Administracji Skarbowej w Rzeszowie");		
		kodyOddzia³ów.put("401030", "Oddzia³ Celny w Medyce, Izba Administracji Skarbowej w Rzeszowie");	
		kodyOddzia³ów.put("401040", "Oddzia³ Celny Kolejowy Przemyœl-Medyka, Izba Administracji Skarbowej w Rzeszowie");		
		kodyOddzia³ów.put("401060", "Oddzia³ Celny w Korczowej, Izba Administracji Skarbowej w Rzeszowie");
		kodyOddzia³ów.put("402010", "Oddzia³ Celny w Rzeszowie, Izba Administracji Skarbowej w Rzeszowie");		
		kodyOddzia³ów.put("402020", "Oddzia³ Celny Port Lotniczy Rzeszów-Jasionka, Izba Administracji Skarbowej w Rzeszowie");	
		kodyOddzia³ów.put("402050", "Oddzia³ Celny w Stalowej Woli, Izba Administracji Skarbowej w Rzeszowie");		
		kodyOddzia³ów.put("402060", "Oddzia³ Celny w Mielcu, Izba Administracji Skarbowej w Rzeszowie");	
		kodyOddzia³ów.put("404010", "Oddzia³ Celny w Kroœnie, Izba Administracji Skarbowej w Rzeszowie");	
		
		kodyOddzia³ów.put("411010", "Oddzia³ Celny w Zielonej Górze, Izba Administracji Skarbowej w Zielonej Górze");	
		kodyOddzia³ów.put("411020", "Oddzia³ Celny w Olszynie, Izba Administracji Skarbowej w Zielonej Górze");		
		kodyOddzia³ów.put("412010", "Oddzia³ Celny w Gorzowie Wielkopolskim, Izba Administracji Skarbowej w Zielonej Górze");	
		kodyOddzia³ów.put("412020", "Oddzia³ Celny w Œwiecku, Izba Administracji Skarbowej w Zielonej Górze");		
		
		kodyOddzia³ów.put("421010", "Oddzia³ Celny w Szczecinie, Izba Administracji Skarbowej w Szczecinie");		
		kodyOddzia³ów.put("421030", "Oddzia³ Celny „Nabrze¿e £asztownia” w Szczecinie, Izba Administracji Skarbowej w Szczecinie");	
		kodyOddzia³ów.put("421050", "Oddzia³ Celny Port Lotniczy Szczecin-Goleniów, Izba Administracji Skarbowej w Szczecinie");		
		kodyOddzia³ów.put("421060", "Oddzia³ Celny w Stargardzie, Izba Administracji Skarbowej w Szczecinie");
		kodyOddzia³ów.put("421080", "Oddzia³ Celny w Œwinoujœciu, Izba Administracji Skarbowej w Szczecinie");		
		kodyOddzia³ów.put("422010", "Oddzia³ Celny w Koszalinie, Izba Administracji Skarbowej w Szczecinie");	
		kodyOddzia³ów.put("422020", "Oddzia³ Celny w Ko³obrzegu, Izba Administracji Skarbowej w Szczecinie");		
		kodyOddzia³ów.put("422030", "Oddzia³ Celny w Szczecinku, Izba Administracji Skarbowej w Szczecinie");
		
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
