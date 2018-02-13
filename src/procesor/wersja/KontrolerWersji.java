package procesor.wersja;

import procesor.raporty.GeneratorRaportów;
import procesor.wersja.wejscie.AktualnaWersja;

public class KontrolerWersji {
	private static KontrolerWersji instancja;
	
	private KontrolerWersji() {}
	
	public static KontrolerWersji pobierzInstancjê() {
		if (instancja == null) {
			instancja = new KontrolerWersji();
		} 		
		return instancja;
	}
	
	public String pobierzOpis() {
		return AktualnaWersja.pobierzInstancjê().pobierzOpis();
	}
	
	public AktualnaWersja pobierzAktualn¹Wersjê() {
		return AktualnaWersja.pobierzInstancjê();
	}
}
