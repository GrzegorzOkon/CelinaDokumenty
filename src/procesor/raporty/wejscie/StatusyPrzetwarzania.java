package procesor.raporty.wejscie;

import java.util.*;

/**
 * Klasa wzorca singleton inicjalizuj�ca i zwracaj�ca opisy status�w dokument�w.
 * 
 * @author Grzegorz Oko�
 */
public class StatusyPrzetwarzania {
	private static volatile StatusyPrzetwarzania instancja;
	
	private Map<String, String> statusyCntrValidDok;
	
	private Map<String, String> statusyDHU;
	private Map<String, String> statusyDP;
	private Map<String, String> statusyDPDZ;
	private Map<String, String> statusyDPZ2;
	private Map<String, String> statusyDS;
	private Map<String, String> statusyDS2;
	private Map<String, String> statusyDSP;
	private Map<String, String> statusyPWD2;
	private Map<String, String> statusySAD2;
	
	public static StatusyPrzetwarzania pobierzInstancj�() {
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
				case "DP" : return pobierzStatusDP(statusPrzetwarzania);
				case "DPDZ" : return pobierzStatusDPDZ(statusPrzetwarzania);
				case "DPZ2" : return pobierzStatusDPZ2(statusPrzetwarzania);
				case "DS" : return pobierzStatusDS(statusPrzetwarzania);
				case "DS2" : return pobierzStatusDS2(statusPrzetwarzania);
				case "DSP" : return pobierzStatusDSP(statusPrzetwarzania);
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
		statusyCntrValidDok.put("O", "oczekiwanie na przes�anie do jednostki");
		statusyCntrValidDok.put("S", "inicjalny, oczekiwanie na walidacj� formaln�");
		statusyCntrValidDok.put("U", "anulowany przez u�ytkownika ");
		statusyCntrValidDok.put("X", "status nie wyst�puje w kodzie aplikacji, prawdopodobnie nadawany r�cznie");
		statusyCntrValidDok.put("Z", "zako�czono obs�ug�");
		statusyCntrValidDok.put("W", "dokument oczekuje na ponown� weryfikacj� podpisu");
		statusyCntrValidDok.put("_", "inicjalny dla dokument�w IST przes�anych webcel-em");
		statusyCntrValidDok.put("*", "nadawany dla deklaracji Intrastat, dla kt�rych wyst�pi� b��d");
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
		statusyDHU.put("N", "do wyja�nienia");
		statusyDHU.put("O", "w rejestracji");
		statusyDHU.put("Q", "odrzucony");
		statusyDHU.put("T", "dla prawnika");
		statusyDHU.put("U", "anulowany");
		statusyDHU.put("X", "zamkni�ty");
		statusyDHU.put("Y", "przyj�ty");
	}
	
	private String pobierzStatusDP(String statusPrzetwarzania) {
		if (statusyDP == null) {
			inicjalizujStatusyDP();
		} 	
		
		return statusyDP.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyDP() {
		statusyDP = new HashMap<>();
		
		statusyDP.put("F", "po awarii");
		statusyDP.put("O", "w rejestracji");
		statusyDP.put("U", "anulowany");
		statusyDP.put("Y", "zarejestrowany");
	}
	
	private String pobierzStatusDPDZ(String statusPrzetwarzania) {
		if (statusyDPDZ == null) {
			inicjalizujStatusyDPDZ();
		} 	
		
		return statusyDPDZ.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyDPDZ() {
		statusyDPDZ = new HashMap<>();
		
		statusyDPDZ.put("F", "po awarii");
		statusyDPDZ.put("G", "po kontroli");
		statusyDPDZ.put("N", "do wyja�nienia");
		statusyDPDZ.put("O", "w rejestracji");
		statusyDPDZ.put("Q", "odrzucony");
		statusyDPDZ.put("T", "dla prawnika");
		statusyDPDZ.put("U", "anulowany");
		statusyDPDZ.put("X", "zamkni�ty");
		statusyDPDZ.put("Y", "przyj�ty");
	}
	
	private String pobierzStatusDPZ2(String statusPrzetwarzania) {
		if (statusyDPZ2 == null) {
			inicjalizujStatusyDPZ2();
		} 	
		
		return statusyDPZ2.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyDPZ2() {
		statusyDPZ2 = new HashMap<>();
		
		statusyDPZ2.put("F", "po awarii");
		statusyDPZ2.put("G", "po kontroli");
		statusyDPZ2.put("N", "do wyja�nienia");
		statusyDPZ2.put("O", "w rejestracji");
		statusyDPZ2.put("Q", "odrzucony");
		statusyDPZ2.put("T", "dla prawnika");
		statusyDPZ2.put("U", "anulowany");
		statusyDPZ2.put("X", "zamkni�ty");
		statusyDPZ2.put("Y", "przyj�ty");
	}

	private String pobierzStatusDS(String statusPrzetwarzania) {
		if (statusyDS == null) {
			inicjalizujStatusyDS();
		} 	
		
		return statusyDS.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyDS() {
		statusyDS = new HashMap<>();
		
		statusyDS.put("F", "po awarii");
		statusyDS.put("G", "po kontroli");
		statusyDS.put("N", "do wyja�nienia");
		statusyDS.put("O", "w rejestracji");
		statusyDS.put("Q", "odrzucony");
		statusyDS.put("R", "oczekuj�cy");
		statusyDS.put("T", "dla prawnika");
		statusyDS.put("U", "anulowany");
		statusyDS.put("X", "zamkni�ty");
		statusyDS.put("Y", "przyj�ty");
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
		statusyDS2.put("K", "uniewa�niony");
		statusyDS2.put("N", "do wyja�nienia");
		statusyDS2.put("O", "w rejestracji");
		statusyDS2.put("Q", "odrzucony");
		statusyDS2.put("R", "oczekuj�cy");
		statusyDS2.put("T", "dla prawnika");
		statusyDS2.put("U", "anulowany");
		statusyDS2.put("X", "zamkni�ty");
		statusyDS2.put("Y", "przyj�ty");
	}
	
	private String pobierzStatusDSP(String statusPrzetwarzania) {
		if (statusyDSP == null) {
			inicjalizujStatusyDSP();
		} 	
		
		return statusyDSP.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyDSP() {
		statusyDSP = new HashMap<>();
		
		statusyDSP.put("F", "po awarii");
		statusyDSP.put("O", "w rejestracji");
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
