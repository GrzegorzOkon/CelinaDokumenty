package procesor.wersja;

import procesor.raporty.GeneratorRaport�w;
import procesor.wersja.wejscie.AktualnaWersja;

public class KontrolerWersji {
	private static KontrolerWersji instancja;
	
	private KontrolerWersji() {}
	
	public static KontrolerWersji pobierzInstancj�() {
		if (instancja == null) {
			instancja = new KontrolerWersji();
		} 		
		return instancja;
	}
	
	public String pobierzOpis() {
		return AktualnaWersja.pobierzInstancj�().pobierzOpis();
	}
	
	public AktualnaWersja pobierzAktualn�Wersj�() {
		return AktualnaWersja.pobierzInstancj�();
	}
}
