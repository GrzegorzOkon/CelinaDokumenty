package procesor.wersja;

import procesor.raporty.GeneratorRaportów;
import procesor.wersja.wejscie.AktualnaWersja;

public class KontrolerWersji {
	private static KontrolerWersji instancja;
	
	private KontrolerWersji() {}
	
	public static KontrolerWersji pobierzInstancję() {
		if (instancja == null) {
			instancja = new KontrolerWersji();
		} 		
		return instancja;
	}
	
	public String pobierzOpis() {
		return AktualnaWersja.pobierzInstancję().pobierzOpis();
	}
	
	public AktualnaWersja pobierzAktualnąWersję() {
		return AktualnaWersja.pobierzInstancję();
	}
}
