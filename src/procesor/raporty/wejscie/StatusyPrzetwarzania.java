package procesor.raporty.wejscie;

import java.util.*;

/**
 * Klasa wzorca singleton inicjalizuj�ca i zwracaj�ca opisy status�w dokument�w.
 * 
 * @author Grzegorz Oko�
 */
public class StatusyPrzetwarzania {
	private static volatile StatusyPrzetwarzania instancja;
	
	private Map<String, String> statusyPODWCntrValidDok;
	private Map<String, String> statusyPZCWCntrValidDok;
	private Map<String, String> statusyCntrValidDok;
	
	private Map<String, String> statusyDHU;
	private Map<String, String> statusyDP;
	private Map<String, String> statusyDPDZ;
	private Map<String, String> statusyDPZ2;
	private Map<String, String> statusyDS;
	private Map<String, String> statusyDS2;
	private Map<String, String> statusyDSP;
	private Map<String, String> statusyDT;
	private Map<String, String> statusyDTG;
	private Map<String, String> statusyDTGN;
	private Map<String, String> statusyDTN;
	private Map<String, String> statusyDTOW;
	private Map<String, String> statusyINNY;
	private Map<String, String> statusyIPT;
	private Map<String, String> statusyISDZ;
	private Map<String, String> statusyISTD;
	private Map<String, String> statusyISTP;
	private Map<String, String> statusyPOD;
	private Map<String, String> statusyPPZC;
	private Map<String, String> statusyPSP;
	private Map<String, String> statusyPW;
	private Map<String, String> statusyPWD;
	private Map<String, String> statusyPWD2;
	private Map<String, String> statusyPZC;
	private Map<String, String> statusySAD;
	private Map<String, String> statusySAD2;
	private Map<String, String> statusySADE;
	private Map<String, String> statusySADG;
	private Map<String, String> statusySADS;
	private Map<String, String> statusySDS;
	private Map<String, String> statusySPWD;
	private Map<String, String> statusySSAD;
	private Map<String, String> statusyST;
	private Map<String, String> statusyTMG;
	private Map<String, String> statusyTST;
	private Map<String, String> statusyWOU;
	
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
				case "POD" : return pobierzStatusPODWCntrValidDok(statusPrzetwarzania);
				case "PZC" : return pobierzStatusPZCWCntrValidDok(statusPrzetwarzania);
				
				default : return pobierzStatusCntrValidDok(statusPrzetwarzania);
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
				case "DT" : return pobierzStatusDT(statusPrzetwarzania);
				case "DTG" : return pobierzStatusDTG(statusPrzetwarzania);
				case "DTGN" : return pobierzStatusDTGN(statusPrzetwarzania);
				case "DTN" : return pobierzStatusDTN(statusPrzetwarzania);
				case "DTOW" : return pobierzStatusDTOW(statusPrzetwarzania);
				case "INNY" : return pobierzStatusINNY(statusPrzetwarzania);
				case "IPT" : return pobierzStatusIPT(statusPrzetwarzania);
				case "ISDZ" : return pobierzStatusISDZ(statusPrzetwarzania);
				case "ISTD" : return pobierzStatusISTD(statusPrzetwarzania);
				case "ISTP" : return pobierzStatusISTP(statusPrzetwarzania);
				case "POD" : return pobierzStatusPOD(statusPrzetwarzania);
				case "PPZC" : return pobierzStatusPPZC(statusPrzetwarzania);
				case "PSP" : return pobierzStatusPSP(statusPrzetwarzania);
				case "PW" : return pobierzStatusPW(statusPrzetwarzania);
				case "PWD" : return pobierzStatusPWD(statusPrzetwarzania);
				case "PWD2" : return pobierzStatusPWD2(statusPrzetwarzania);
				case "PZC" : return pobierzStatusPZC(statusPrzetwarzania);
				case "SAD" : return pobierzStatusSAD(statusPrzetwarzania);
				case "SAD2" : return pobierzStatusSAD2(statusPrzetwarzania);
				case "SADE" : return pobierzStatusSADE(statusPrzetwarzania);
				case "SADG" : return pobierzStatusSADG(statusPrzetwarzania);
				case "SADS" : return pobierzStatusSADS(statusPrzetwarzania);
				case "SDS" : return pobierzStatusSDS(statusPrzetwarzania);
				case "SPWD" : return pobierzStatusSPWD(statusPrzetwarzania);
				case "SSAD" : return pobierzStatusSSAD(statusPrzetwarzania);
				case "ST" : return pobierzStatusST(statusPrzetwarzania);
				case "TMG" : return pobierzStatusTMG(statusPrzetwarzania);
				case "TST" : return pobierzStatusTST(statusPrzetwarzania);
				case "WOU" : return pobierzStatusWOU(statusPrzetwarzania);
				
				default : return null;
			}
		}
	}
	
	private String pobierzStatusPODWCntrValidDok(String statusPrzetwarzania) {
		if (statusyPODWCntrValidDok == null) {
			inicjalizujStatusyPODWCntrValidDok();
		} 	
		
		return statusyPODWCntrValidDok.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyPODWCntrValidDok() {
		statusyPODWCntrValidDok = new HashMap<>();
		
		statusyPODWCntrValidDok.put("F", "walidacja formalna (w trakcie)");
		statusyPODWCntrValidDok.put("G", "udost�pniony");
		statusyPODWCntrValidDok.put("N", "dokument niepoprawny");
		statusyPODWCntrValidDok.put("O", "oczekiwanie na przes�anie do jednostki");
		statusyPODWCntrValidDok.put("P", "do udost�pnienia");
		statusyPODWCntrValidDok.put("S", "inicjalny, oczekiwanie na walidacj� formaln�");
		statusyPODWCntrValidDok.put("U", "anulowany przez u�ytkownika ");
		statusyPODWCntrValidDok.put("X", "utworzony");
		statusyPODWCntrValidDok.put("X", "potwierdzono powiadomienie");
		statusyPODWCntrValidDok.put("Z", "zako�czono obs�ug�");
		statusyPODWCntrValidDok.put("W", "dokument oczekuje na ponown� weryfikacj� podpisu");
		statusyPODWCntrValidDok.put("_", "inicjalny dla dokument�w IST przes�anych webcel-em");
		statusyPODWCntrValidDok.put("*", "nadawany dla deklaracji Intrastat, dla kt�rych wyst�pi� b��d");
	}
	
	private String pobierzStatusPZCWCntrValidDok(String statusPrzetwarzania) {
		if (statusyPZCWCntrValidDok == null) {
			inicjalizujStatusyPZCWCntrValidDok();
		} 	
		
		return statusyPZCWCntrValidDok.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyPZCWCntrValidDok() {
		statusyPZCWCntrValidDok = new HashMap<>();
		
		statusyPZCWCntrValidDok.put("F", "walidacja formalna (w trakcie)");
		statusyPZCWCntrValidDok.put("N", "dokument niepoprawny");
		statusyPZCWCntrValidDok.put("O", "wygenerowany");
		statusyPZCWCntrValidDok.put("P", "udost�pniony");
		statusyPZCWCntrValidDok.put("S", "inicjalny, oczekiwanie na walidacj� formaln�");
		statusyPZCWCntrValidDok.put("U", "anulowany przez u�ytkownika ");
		statusyPZCWCntrValidDok.put("X", "pobrany");
		statusyPZCWCntrValidDok.put("Y", "podpisany");
		statusyPZCWCntrValidDok.put("Z", "zako�czono obs�ug�");
		statusyPZCWCntrValidDok.put("W", "dokument oczekuje na ponown� weryfikacj� podpisu");
		statusyPZCWCntrValidDok.put("_", "inicjalny dla dokument�w IST przes�anych webcel-em");
		statusyPZCWCntrValidDok.put("*", "nadawany dla deklaracji Intrastat, dla kt�rych wyst�pi� b��d");
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

	private String pobierzStatusDT(String statusPrzetwarzania) {
		if (statusyDT == null) {
			inicjalizujStatusyDT();
		} 	
		
		return statusyDT.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyDT() {
		statusyDT = new HashMap<>();
		
		statusyDT.put("F", "po awarii");
		statusyDT.put("G", "po kontroli");
		statusyDT.put("N", "do wyja�nienia");
		statusyDT.put("O", "w rejestracji");
		statusyDT.put("Q", "odrzucony");
		statusyDT.put("R", "oczekuj�cy");
		statusyDT.put("T", "dla prawnika");
		statusyDT.put("U", "anulowany");
		statusyDT.put("X", "zamkni�ty");
		statusyDT.put("Y", "przyj�ty");
	}

	private String pobierzStatusDTG(String statusPrzetwarzania) {
		if (statusyDTG == null) {
			inicjalizujStatusyDTG();
		} 	
		
		return statusyDTG.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyDTG() {
		statusyDTG = new HashMap<>();
		
		statusyDTG.put("F", "po awarii");
		statusyDTG.put("G", "po kontroli");
		statusyDTG.put("N", "do wyja�nienia");
		statusyDTG.put("O", "w rejestracji");
		statusyDTG.put("P", "przyj�ty");
		statusyDTG.put("Q", "odrzucony");
		statusyDTG.put("T", "dla prawnika");
		statusyDTG.put("U", "anulowany");
		statusyDTG.put("X", "zamkni�ty");
		statusyDTG.put("Y", "przyj�ty");
	}

	private String pobierzStatusDTGN(String statusPrzetwarzania) {
		if (statusyDTGN == null) {
			inicjalizujStatusyDTGN();
		} 	
		
		return statusyDTGN.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyDTGN() {
		statusyDTGN = new HashMap<>();
		
		statusyDTGN.put("F", "po awarii");
		statusyDTGN.put("G", "po kontroli");
		statusyDTGN.put("N", "do wyja�nienia");
		statusyDTGN.put("O", "w rejestracji");
		statusyDTGN.put("P", "przyj�ty");
		statusyDTGN.put("Q", "odrzucony");
		statusyDTGN.put("T", "dla prawnika");
		statusyDTGN.put("U", "anulowany");
		statusyDTGN.put("X", "zamkni�ty");
		statusyDTGN.put("Y", "przyj�ty");
	}

	private String pobierzStatusDTN(String statusPrzetwarzania) {
		if (statusyDTN == null) {
			inicjalizujStatusyDTN();
		} 	
		
		return statusyDTN.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyDTN() {
		statusyDTN = new HashMap<>();
		
		statusyDTN.put("F", "po awarii");
		statusyDTN.put("G", "po kontroli");
		statusyDTN.put("N", "do wyja�nienia");
		statusyDTN.put("Q", "odrzucony");
		statusyDTN.put("R", "oczekuj�cy");
		statusyDTN.put("T", "dla prawnika");
		statusyDTN.put("U", "anulowany");
		statusyDTN.put("X", "zamkni�ty");
		statusyDTN.put("Y", "przyj�ty");
	}
	
	private String pobierzStatusDTOW(String statusPrzetwarzania) {
		if (statusyDTOW == null) {
			inicjalizujStatusyDTOW();
		} 	
		
		return statusyDTOW.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyDTOW() {
		statusyDTOW = new HashMap<>();
		
		statusyDTOW.put("F", "po awarii");
	}
	
	private String pobierzStatusINNY(String statusPrzetwarzania) {
		if (statusyINNY == null) {
			inicjalizujStatusyINNY();
		} 	
		
		return statusyINNY.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyINNY() {
		statusyINNY = new HashMap<>();
		
		statusyINNY.put("F", "po awarii");
		statusyINNY.put("G", "po kontroli");
		statusyINNY.put("N", "do wyja�nienia");
		statusyINNY.put("O", "w rejestracji");
		statusyINNY.put("Q", "odrzucony");
		statusyINNY.put("T", "dla prawnika");
		statusyINNY.put("U", "anulowany");
		statusyINNY.put("X", "zamkni�ty");
		statusyINNY.put("Y", "przyj�ty");
	}
	
	private String pobierzStatusIPT(String statusPrzetwarzania) {
		if (statusyIPT == null) {
			inicjalizujStatusyIPT();
		} 	
		
		return statusyIPT.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyIPT() {
		statusyIPT = new HashMap<>();
		
		statusyIPT.put("R", "oczekuj�cy");
		statusyIPT.put("U", "anulowany");
		statusyIPT.put("X", "zamkni�ty");
	}
	
	private String pobierzStatusISDZ(String statusPrzetwarzania) {
		if (statusyISDZ == null) {
			inicjalizujStatusyISDZ();
		} 	
		
		return statusyISDZ.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyISDZ() {
		statusyISDZ = new HashMap<>();
		
		statusyISDZ.put("F", "po awarii");
		statusyISDZ.put("N", "do wyja�nienia");		
		statusyISDZ.put("O", "w rejestracji");
		statusyISDZ.put("R", "oczekuj�cy");
		statusyISDZ.put("Y", "zarejestrowany");
	}

	private String pobierzStatusISTD(String statusPrzetwarzania) {
		if (statusyISTD == null) {
			inicjalizujStatusyISTD();
		} 	
		
		return statusyISTD.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyISTD() {
		statusyISTD = new HashMap<>();
			
		statusyISTD.put("O", "do identyfikacji");
		statusyISTD.put("U", "anulowany");
		statusyISTD.put("Y", "zidentyfikowany");
	}
	
	private String pobierzStatusISTP(String statusPrzetwarzania) {
		if (statusyISTP == null) {
			inicjalizujStatusyISTP();
		} 	
		
		return statusyISTP.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyISTP() {
		statusyISTP = new HashMap<>();
			
		statusyISTP.put("A", "anulowany");
		statusyISTP.put("O", "do zatwierdzenia");
		statusyISTP.put("Q", "b��d logiczny");
		statusyISTP.put("U", "b��d formalny");
		statusyISTP.put("Y", "zatwierdzony");
	}
	
	private String pobierzStatusPOD(String statusPrzetwarzania) {
		if (statusyPOD == null) {
			inicjalizujStatusyPOD();
		} 	
		
		return statusyPOD.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyPOD() {
		statusyPOD = new HashMap<>();
		
		statusyPOD.put("G", "udost�pniony");
		statusyPOD.put("O", "utworzony");	
		statusyPOD.put("P", "do udost�pnienia");		
		statusyPOD.put("X", "utworzony");
		statusyPOD.put("Y", "potwierdzono powiadomienie");
	}
	
	private String pobierzStatusPPZC(String statusPrzetwarzania) {
		if (statusyPPZC == null) {
			inicjalizujStatusyPPZC();
		} 	
		
		return statusyPPZC.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyPPZC() {
		statusyPPZC = new HashMap<>();
		
		statusyPPZC.put("Y", "wygenerowany");
	}
	
	private String pobierzStatusPSP(String statusPrzetwarzania) {
		if (statusyPSP == null) {
			inicjalizujStatusyPSP();
		} 	
		
		return statusyPSP.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyPSP() {
		statusyPSP = new HashMap<>();
		
		statusyPSP.put("F", "po awarii");	
		statusyPSP.put("G", "po kontroli");	
		statusyPSP.put("N", "do wyja�nienia");
		statusyPSP.put("O", "w rejestracji");		
		statusyPSP.put("Q", "odrzucony");
		statusyPSP.put("T", "dla prawnika");	
		statusyPSP.put("U", "anulowany");
		statusyPSP.put("X", "zamkni�ty");
		statusyPSP.put("Y", "przyj�ty");
	}
	
	private String pobierzStatusPW(String statusPrzetwarzania) {
		if (statusyPW == null) {
			inicjalizujStatusyPW();
		} 	
		
		return statusyPW.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyPW() {
		statusyPW = new HashMap<>();
		
		statusyPW.put("F", "po awarii");	
		statusyPW.put("G", "po kontroli");			
		statusyPW.put("N", "do wyja�nienia");
		statusyPW.put("O", "w rejestracji");	
		statusyPW.put("U", "anulowany");
		statusyPW.put("X", "zamkni�ty");
	}
	
	private String pobierzStatusPWD(String statusPrzetwarzania) {
		if (statusyPWD == null) {
			inicjalizujStatusyPWD();
		} 	
		
		return statusyPWD.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyPWD() {
		statusyPWD = new HashMap<>();
		
		statusyPWD.put("F", "po awarii");		
		statusyPWD.put("G", "po kontroli");
		statusyPWD.put("N", "do wyja�nienia");
		statusyPWD.put("O", "w rejestracji");
		statusyPWD.put("Q", "odrzucony");
		statusyPWD.put("R", "oczekuj�cy");	
		statusyPWD.put("T", "dla prawnika");	
		statusyPWD.put("U", "anulowany");	
		statusyPWD.put("X", "zamkni�ty");
		statusyPWD.put("Y", "przyj�ty");
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
	
	private String pobierzStatusPZC(String statusPrzetwarzania) {
		if (statusyPZC == null) {
			inicjalizujStatusyPZC();
		} 	
		
		return statusyPZC.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyPZC() {
		statusyPZC = new HashMap<>();
		
		statusyPZC.put("O", "wygenerowany");
		statusyPZC.put("P", "udost�pniony");
		statusyPZC.put("X", "pobrany");
		statusyPZC.put("Y", "podpisany");
	}
	
	private String pobierzStatusSAD(String statusPrzetwarzania) {
		if (statusySAD == null) {
			inicjalizujStatusySAD();
		} 	
		
		return statusySAD.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusySAD() {
		statusySAD = new HashMap<>();
		
		statusySAD.put("C", "odrzucony");
		statusySAD.put("F", "po awarii");
		statusySAD.put("G", "po kontroli");
		statusySAD.put("N", "do wyja�nienia");
		statusySAD.put("O", "w rejestracji");
		statusySAD.put("R", "oczekuj�cy");
		statusySAD.put("T", "dla prawnika");	
		statusySAD.put("U", "anulowany");
		statusySAD.put("X", "zamkni�ty");
		statusySAD.put("Y", "przyj�ty");
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

	private String pobierzStatusSADE(String statusPrzetwarzania) {
		if (statusySADE == null) {
			inicjalizujStatusySADE();
		} 	
		
		return statusySADE.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusySADE() {
		statusySADE = new HashMap<>();
		
		statusySADE.put("F", "po awarii");
		statusySADE.put("O", "otwarty");
	}
	
	private String pobierzStatusSADG(String statusPrzetwarzania) {
		if (statusySADG == null) {
			inicjalizujStatusySADG();
		} 	
		
		return statusySADG.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusySADG() {
		statusySADG = new HashMap<>();
		
		statusySADG.put("F", "po awarii");	
		statusySADG.put("G", "po kontroli");
		statusySADG.put("N", "do wyja�nienia");
		statusySADG.put("O", "w rejestracji");
		statusySADG.put("P", "przyj�ty");
		statusySADG.put("Q", "odrzucony");
		statusySADG.put("T", "dla prawnika");
		statusySADG.put("U", "anulowany");
		statusySADG.put("X", "zamkni�ty");
		statusySADG.put("Y", "przyj�ty");
	}
	
	private String pobierzStatusSADS(String statusPrzetwarzania) {
		if (statusySADS == null) {
			inicjalizujStatusySADS();
		} 	
		
		return statusySADS.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusySADS() {
		statusySADS = new HashMap<>();
		
		statusySADS.put("A", "w centrali");
		statusySADS.put("O", "otrzymany");
		statusySADS.put("S", "do centrali");
		statusySADS.put("W", "zwroty do centrali");
		statusySADS.put("X", "po weryfikacji");
		statusySADS.put("Y", "w weryfikacji");
	}
	
	private String pobierzStatusSDS(String statusPrzetwarzania) {
		if (statusySDS == null) {
			inicjalizujStatusySDS();
		} 	
		
		return statusySDS.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusySDS() {
		statusySDS = new HashMap<>();
		
		statusySDS.put("Q", "odrzucony");
		statusySDS.put("R", "oczekuj�cy");
		statusySDS.put("Y", "naniesiony");
	}
	
	private String pobierzStatusSPWD(String statusPrzetwarzania) {
		if (statusySPWD == null) {
			inicjalizujStatusySPWD();
		} 	
		
		return statusySPWD.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusySPWD() {
		statusySPWD = new HashMap<>();
		
		statusySPWD.put("Q", "odrzucony");
		statusySPWD.put("R", "oczekuj�cy");
		statusySPWD.put("Y", "naniesiony");
	}
	
	private String pobierzStatusSSAD(String statusPrzetwarzania) {
		if (statusySSAD == null) {
			inicjalizujStatusySSAD();
		} 	
		
		return statusySSAD.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusySSAD() {
		statusySSAD = new HashMap<>();
		
		statusySSAD.put("Q", "odrzucony");
		statusySSAD.put("R", "oczekuj�cy");
		statusySSAD.put("Y", "naniesiony");
	}	
	
	private String pobierzStatusST(String statusPrzetwarzania) {
		if (statusyST == null) {
			inicjalizujStatusyST();
		} 	
		
		return statusyST.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyST() {
		statusyST = new HashMap<>();
		
		statusyST.put("F", "po awarii");	
		statusyST.put("G", "po kontroli");	
		statusyST.put("N", "do wyja�nienia");	
		statusyST.put("O", "w rejestracji");	
		statusyST.put("Q", "odrzucony");
		statusyST.put("T", "dla prawnika");
		statusyST.put("U", "anulowany");
		statusyST.put("X", "zamkni�ty");
		statusyST.put("Y", "przyj�ty");
	}	
	
	private String pobierzStatusTMG(String statusPrzetwarzania) {
		if (statusyTMG == null) {
			inicjalizujStatusyTMG();
		} 	
		
		return statusyTMG.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyTMG() {
		statusyTMG = new HashMap<>();
		
		statusyTMG.put("F", "po awarii");		
		statusyTMG.put("G", "zamkni�ty");	
		statusyTMG.put("O", "w rejestracji");
		statusyTMG.put("P", "oczekuj�cy");
		statusyTMG.put("Y", "zatwierdzony");
	}	
	
	private String pobierzStatusTST(String statusPrzetwarzania) {
		if (statusyTST == null) {
			inicjalizujStatusyTST();
		} 	
		
		return statusyTST.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyTST() {
		statusyTST = new HashMap<>();
		
		statusyTST.put("F", "po awarii");	
		statusyTST.put("G", "do zamkni�cia");	
		statusyTST.put("N", "nieprawid�owy");
		statusyTST.put("O", "otwarty");
		statusyTST.put("Y", "zatwierdzony");
	}	
		
	private String pobierzStatusWOU(String statusPrzetwarzania) {
		if (statusyWOU == null) {
			inicjalizujStatusyWOU();
		} 	
		
		return statusyWOU.get(statusPrzetwarzania);
	}
	
	private void inicjalizujStatusyWOU() {
		statusyWOU = new HashMap<>();
				
		statusyWOU.put("Q", "odrzucony");
		statusyWOU.put("T", "dla prawnika");
		statusyWOU.put("R", "oczekuj�cy");
		statusyWOU.put("Y", "zaakceptowany");
	}	
}
