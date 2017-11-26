package procesor.raporty.wejscie;

import java.util.*;

/**
 * Klasa inicjalizuj�ca i zwracaj�ca opisy status�w dokument�w dla przes�anych symboli.
 * 
 * @author Grzegorz Oko�
 */
public class StatusyPrzetwarzania {
	private static StatusyPrzetwarzania instancja;
	
	private Map<String, String> statusyPWD2;
	private Map<String, String> statusySAD2;
	
	public static StatusyPrzetwarzania pobierzInstancj�() {
		if (instancja == null) {
			instancja = new StatusyPrzetwarzania();
		} 		
		return instancja;
	}
	
	public String pobierzStatus(String rodzajDokumentu, String statusPrzetwarzania) {
		switch(rodzajDokumentu) {
			case "PWD2" : return pobierzStatusPWD2(statusPrzetwarzania);
			case "SAD2" : return pobierzStatusSAD2(statusPrzetwarzania);
			
			default : return null;
		}
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
		statusyPWD2.put("K", "uniewa�niony");
		statusyPWD2.put("N", "do wyja�nienia");
		statusyPWD2.put("O", "w rejestracji");
		statusyPWD2.put("Q", "odrzucony");
		statusyPWD2.put("R", "oczekuj�cy");
		statusyPWD2.put("T", "dla prawnika");
		statusyPWD2.put("U", "anulowany");
		statusyPWD2.put("X", "zamkni�ty");
		statusyPWD2.put("Y", "przyj�ty");
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
		statusySAD2.put("N", "do wyja�nienia");
		statusySAD2.put("O", "w rejestracji");
		statusySAD2.put("K", "uniewa�niony");
		statusySAD2.put("R", "oczekuj�cy");
		statusySAD2.put("T", "dla prawnika");
		statusySAD2.put("U", "anulowany");
		statusySAD2.put("X", "zamkni�ty");
		statusySAD2.put("Y", "przyj�ty");
	}
	
	private void inicjalizujStatusyDHU() {}
	
	private void inicjalizujStatusyDP() {}
	
	private void inicjalizujStatusyDPDZ() {}
	
	private void inicjalizujStatusyDPZ2() {}
	
	private void inicjalizujStatusyDS() {}
	
	private void inicjalizujStatusyDS2() {}
		
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
