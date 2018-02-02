package procesor.raporty.wejscie;

import java.util.*;

/**
 * Klasa wzorca singleton inicjalizuj¹ca i zwracaj¹ca opisy statusów dokumentów.
 * 
 * @author Grzegorz Okoñ
 */
public class StatusyPrzetwarzania {
	private static volatile StatusyPrzetwarzania instancja;
	
	private Map<String, String> statusyCntrValidDok;
	
	private Map<String, String> statusyDHU;
	private Map<String, String> statusyDS2;
	private Map<String, String> statusyPWD2;
	private Map<String, String> statusySAD2;
	
	public static StatusyPrzetwarzania pobierzInstancjê() {
		if (instancja == null) {
			synchronized(StatusyPrzetwarzania.class) {
				if(instancja == null) {
					instancja = new StatusyPrzetwarzania();
				}
			}
		} 	
		
		return instancja;
	}
	
	public String pobierzOpisStatusu(String rodzajDokumentu, String statusPrzetwarzania, Tabela tabela) {
		if (tabela.equals(tabela.CNTR_VALID_DOK)) {
			switch(rodzajDokumentu) {
				case "PWD2" : return pobierzStatusCntrValidDok(statusPrzetwarzania);
			
				default : return null;
			}
		} else {
			switch(rodzajDokumentu) {
				case "DHU" : return pobierzStatusDHU(statusPrzetwarzania);
				case "DS2" : return pobierzStatusDS2(statusPrzetwarzania);
				case "PWD2" : return pobierzStatusPWD2(statusPrzetwarzania);
				case "SAD2" : return pobierzStatusSAD2(statusPrzetwarzania);
				
				default : return null;
			}
		}
	}
	
	private String pobierzStatusCntrValidDok(String statusPrzetwarzania) {
		if (statusyCntrValidDok == null) {
			inicjalizujStatusyCntrValidDok();
		} 	
		
		return statusyCntrValidDok.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyCntrValidDok() {
		statusyCntrValidDok = new HashMap<>();
		
		statusyCntrValidDok.put("F", "walidacja formalna (w trakcie)");
		statusyCntrValidDok.put("N", "dokument niepoprawny");
		statusyCntrValidDok.put("O", "oczekiwanie na przes³anie do jednostki");
		statusyCntrValidDok.put("S", "inicjalny, oczekiwanie na walidacjê formaln¹");
		statusyCntrValidDok.put("U", "anulowany przez u¿ytkownika ");
		statusyCntrValidDok.put("X", "status nie wystêpuje w kodzie aplikacji, prawdopodobnie nadawany rêcznie");
		statusyCntrValidDok.put("Z", "zakoñczono obs³ugê");
		statusyCntrValidDok.put("W", "dokument oczekuje na ponown¹ weryfikacjê podpisu");
		statusyCntrValidDok.put("_", "inicjalny dla dokumentów IST przes³anych webcel-em");
		statusyCntrValidDok.put("*", "nadawany dla deklaracji Intrastat, dla których wyst¹pi³ b³¹d");
	}
	
	private String pobierzStatusDHU(String statusPrzetwarzania) {
		if (statusyDHU == null) {
			inicjalizujStatusyDHU();
		} 	
		
		return statusyDHU.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyDHU() {
		statusyDHU = new HashMap<>();
		
		statusyDHU.put("A", "po awarii");
		statusyDHU.put("F", "po awarii");
		statusyDHU.put("G", "po kontroli");
		statusyDHU.put("N", "do wyjaœnienia");
		statusyDHU.put("O", "w rejestracji");
		statusyDHU.put("Q", "odrzucony");
		statusyDHU.put("T", "dla prawnika");
		statusyDHU.put("U", "anulowany");
		statusyDHU.put("X", "zamkniêty");
		statusyDHU.put("Y", "przyjêty");
	}
	
	private String pobierzStatusDS2(String statusPrzetwarzania) {
		if (statusyDS2 == null) {
			inicjalizujStatusyDS2();
		} 	
		
		return statusyDS2.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyDS2() {
		statusyDS2 = new HashMap<>();
		
		statusyDS2.put("B", "zarejestrowany");
		statusyDS2.put("F", "po awarii");
		statusyDS2.put("G", "po kontroli");
		statusyDS2.put("K", "uniewa¿niony");
		statusyDS2.put("N", "do wyjaœnienia");
		statusyDS2.put("O", "w rejestracji");
		statusyDS2.put("Q", "odrzucony");
		statusyDS2.put("R", "oczekuj¹cy");
		statusyDS2.put("T", "dla prawnika");
		statusyDS2.put("U", "anulowany");
		statusyDS2.put("X", "zamkniêty");
		statusyDS2.put("Y", "przyjêty");
	}
	
	private String pobierzStatusPWD2(String statusPrzetwarzania) {
		if (statusyPWD2 == null) {
			inicjalizujStatusyPWD2();
		} 	
		
		return statusyPWD2.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyPWD2() {
		statusyPWD2 = new HashMap<>();
		
		statusyPWD2.put("F", "po awarii");
		statusyPWD2.put("G", "po kontroli");
		statusyPWD2.put("K", "uniewa¿niony");
		statusyPWD2.put("N", "do wyjaœnienia");
		statusyPWD2.put("O", "w rejestracji");
		statusyPWD2.put("Q", "odrzucony");
		statusyPWD2.put("R", "oczekuj¹cy");
		statusyPWD2.put("T", "dla prawnika");
		statusyPWD2.put("U", "anulowany");
		statusyPWD2.put("X", "zamkniêty");
		statusyPWD2.put("Y", "przyjêty");
	}
	
	private String pobierzStatusSAD2(String statusPrzetwarzania) {
		if (statusySAD2 == null) {
			inicjalizujStatusySAD2();
		} 	
		
		return statusySAD2.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusySAD2() {
		statusySAD2 = new HashMap<>();
		
		statusySAD2.put("B", "zarejestrowany");
		statusySAD2.put("C", "odrzucony");
		statusySAD2.put("F", "po awarii");
		statusySAD2.put("G", "po kontroli");
		statusySAD2.put("N", "do wyjaœnienia");
		statusySAD2.put("O", "w rejestracji");
		statusySAD2.put("K", "uniewa¿niony");
		statusySAD2.put("R", "oczekuj¹cy");
		statusySAD2.put("T", "dla prawnika");
		statusySAD2.put("U", "anulowany");
		statusySAD2.put("X", "zamkniêty");
		statusySAD2.put("Y", "przyjêty");
	}
	
	private void inicjalizujStatusyDP() {}
	
	private void inicjalizujStatusyDPDZ() {}
	
	private void inicjalizujStatusyDPZ2() {}
	
	private void inicjalizujStatusyDS() {}
		
	private void inicjalizujStatusyDSP() {}
		
	private void inicjalizujStatusyDT() {}

	private void inicjalizujStatusyDTG() {}
	
	private void inicjalizujStatusyDTGN() {}
		
	private void inicjalizujStatusyDTN() {}
	
	private void inicjalizujStatusyDTOW() {}
	
	private void inicjalizujStatusyINNY() {}
	
	private void inicjalizujStatusyIPT() {}
		
	private void inicjalizujStatusyISDZ() {}
		
	private void inicjalizujStatusyISTD() {}
	
	private void inicjalizujStatusyISTP() {}
	
	private void inicjalizujStatusyPOD() {}
	
	private void inicjalizujStatusyPPZC() {}
	
	private void inicjalizujStatusyPSP() {}
	
	private void inicjalizujStatusyPW() {}
	
	private void inicjalizujStatusyPWD() {}
		
	private void inicjalizujStatusyPZC() {}

	private void inicjalizujStatusySAD() {}
		
	private void inicjalizujStatusySADE() {}
	
	private void inicjalizujStatusySADG() {}
	
	private void inicjalizujStatusySADS() {}
	
	private void inicjalizujStatusySDS() {}
		
	private void inicjalizujStatusySPWD() {}
		
	private void inicjalizujStatusySSAD() {}	
	
	private void inicjalizujStatusyST() {}
	
	private void inicjalizujStatusyTMG() {}
		
	private void inicjalizujStatusyTST() {}
		
	private void inicjalizujStatusyWOU() {}
}
