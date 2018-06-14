package procesor.raporty;

import java.util.ArrayList;
import java.util.List;

import kontroler.wejscie.Dokument;
import kontroler.wejscie.DokumentZIzby;
import kontroler.wejscie.Identyfikator;
import procesor.dao.sybase.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.sybase.entity.DokumentZCentralaDokumenty;
import procesor.dao.sybase.entity.DokumentZIzbyDokumenty;
import procesor.raporty.wejscie.KodyOddzia³ów;
import procesor.raporty.wejscie.Raport;
import procesor.raporty.wejscie.StatusyPrzetwarzania;
import procesor.raporty.wejscie.Tabela;

public class GeneratorRaportów {
	private static volatile GeneratorRaportów instancja;
	
	private GeneratorRaportów() {}
	
	public static GeneratorRaportów pobierzInstancjê() {
		if (instancja == null) {
			synchronized(GeneratorRaportów.class) {
				if(instancja == null) {
					instancja = new GeneratorRaportów();
				}
			}
		} 		
		return instancja;
	}
	
	public List<Raport> utwórzCentralnyRaport(List<Dokument> dokumenty) {		
		List<Raport> raporty = new ArrayList<>();
		
		for (Dokument dokument : dokumenty) {
			String raportDlaHelpDesku = "";
			String raportDlaAdministratora = "";
			
			// Generowanie raportów dla numerów dla których nie ma dokumentów
			if (dokument.getDokumentyZCentralaCntrValidDok() == null && dokument.getDokumentyZCentralaDokumenty() == null) {
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {	
					
					raportDlaHelpDesku += "Szukanego dokumentu o numerze w³asnym " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " brak w bazie centralnej.";
					
					raportDlaAdministratora += "Szukany nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " nie wystepuje w tabeli cntr_valid_dok.\n\n";
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					
					raportDlaHelpDesku += "Szukanego dokumentu o numerze systemowym " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " brak w bazie centralnej.";
					
					raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " nie wystêpuje w tabelach cntr_valid_dok oraz dokumenty.\n\n";
				} else {
					
					raportDlaHelpDesku += "Szukanego dokumentu o numerze ewidencyjnym " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " brak w bazie centralnej.";	
					
					raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " nie wystêpuje w tabeli dokumenty.\n\n";
				}
			} else if (dokument.getDokumentyZCentralaCntrValidDok() != null && dokument.getDokumentyZCentralaDokumenty() == null) {  //numery s¹ jedynie w cntr_valid_dok
				for (DokumentZCentralaCntrValidDok dokumentCntrValidDok : dokument.getDokumentyZCentralaCntrValidDok()) {
					if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
						
						raportDlaHelpDesku += "Szukany dokument o numerze w³asnym " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje siê w bazie centralnej."
							+ " Ma przypisany numer systemowy " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + "."
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + "."
										: ".")
								: "")	
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? " Jego jednostka przeznaczenia to " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + "."
										: ".")
								: "");	
						
						raportDlaAdministratora += "Szukany nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje siê w tabeli cntr_valid_dok, ale brak go w tabeli dokumenty.\n"
							+ ((dokumentCntrValidDok.getIdentyfikatorDokumentu() != null) 
								? "Ma przypisany id_dok " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + ".\n"
								: "")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + ".\n"
										: " - brak opisu dla danego statusu.\n")
								: "")
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? "Jego jedn_przezn to " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
										: " - brak opisu dla danego kodu.\n\n")
								: "");
					} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
						raportDlaHelpDesku += "Szukany numer systemowy " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje siê w bazie centralnej."
							+ ((dokumentCntrValidDok.getNumerWlasny() != null) 
								? " Dokument ma nadany numer w³asny " + dokumentCntrValidDok.getNumerWlasny() + "."
								: "")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + "."
										: ".")
								: "")	
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? " Ma przypisan¹ jednostkê przeznaczenia " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + "."
										: ".")
								: "");	
						
						raportDlaAdministratora += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true)
								? "id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU)
								: "")
								+ " znajduje siê w tabeli cntr_valid_dok.\n"
							+ ((dokumentCntrValidDok.getNumerWlasny() != null) 
								? "Dokument ma nadany nr_akt " + dokumentCntrValidDok.getNumerWlasny() + ".\n"
								: "\n")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + ".\n"
										: " - brak opisu dla danego statusu.\n")
								: "")
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? "Ma przypisan¹ jedn_przezn " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
										: " - brak opisu dla danego kodu.\n\n")
								: "");	
					}
				}
			} else if (dokument.getDokumentyZCentralaDokumenty() != null) {  //numer jest w dokumentach
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
					for (DokumentZCentralaDokumenty dok : dokument.getDokumentyZCentralaDokumenty()) {	
						
						raportDlaHelpDesku += "Szukany dokument o numerze w³asnym " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje siê w bazie centralnej."					
							+ " Ma przypisany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
							+ ((dok.getSymbolDokumentu() != null)
								? " Posiada numer ewidencyjny " + dok.getSymbolDokumentu() + "."
								: "")
							+ " Jest w statusie " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
								? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + "."
								: ".")	
							+ " Jego jednostka przeznaczenia to " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
								? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + "."
								: ".");	
				
						raportDlaAdministratora += "Dla szukanego nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znaleziono odpowiadaj¹cy dokument w tabeli dokumenty.\n"
							+ "Ma przypisany id_dok " + dok.getIdentyfikatorDokumentu() + ".\n"
							+ ((dok.getSymbolDokumentu() != null) 
								? "Posiada sym_dok " + dok.getSymbolDokumentu() + "\n"
								: "")
							+ "Jest w status_przetw " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
								? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + ".\n"
								: " - brak opisu dla danego statusu.\n")
							+ "Jego jedn_przezn to " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
								? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + ".\n\n"
								: " - brak opisu dla danego kodu.\n\n");
					}
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {			
					for (DokumentZCentralaDokumenty dok : dokument.getDokumentyZCentralaDokumenty()) {	
						
						raportDlaHelpDesku += "Szukany dokument o numerze systemowym " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje siê w bazie centralnej."				
							+ ((dok.getSymbolDokumentu() != null) 
								? " Posiada numer ewidencyjny " + dok.getSymbolDokumentu() + "." 
								: "")
							+ " Jest w statusie " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
								? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + "."
								: ".")
							+ " Jego jednostka przeznaczenia to " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
								? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + "."
								: ".");	
					
						raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje siê w tabeli dokumenty.\n"
							+ ((dok.getSymbolDokumentu() != null) 
								? "Posiada sym_dok " + dok.getSymbolDokumentu() + ".\n" 
								: "Nie posiada sym_dok.\n")
							+ "Jest w status_przetw " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
								? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + ".\n"
								: " - brak opisu dla danego statusu.\n")
							+ "Jego id_jedn to " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
								? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + ".\n\n"
								: " - brak opisu dla danego kodu.\n\n");	
					}
				} else {
					for (DokumentZCentralaDokumenty dok : dokument.getDokumentyZCentralaDokumenty()) {	
						
						raportDlaHelpDesku += "Szukany dokument o numerze ewidencyjnym " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje siê w bazie centralnej."
							+ " Ma przypisany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
							+ " Jest w statusie " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
								? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + "."
								: ".")	
							+ "Jego jednostka przeznaczenia to " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
								? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + "."
								: ".");	
					
						raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje siê w tabeli dokumenty.\n"
							+ "Ma przypisany id_dok " + dok.getIdentyfikatorDokumentu() + ".\n"
							+ "Jest w status_przetw " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
								? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + ".\n"
								: " - brak opisu dla danego statusu.\n")	
							+ "Jego id_jedn to " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
								? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + ".\n\n"
								: " - brak opisu dla danego kodu.\n\n");
					}
				}
			}
			
			raporty.add(new Raport(raportDlaHelpDesku, raportDlaAdministratora));	
		}
		
		return raporty;
	}
	
	
	public List<Raport> utwórzLokalnyRaport(List<DokumentZIzby> dokumenty) {		
		List<Raport> raporty = new ArrayList<>();
		
		for (DokumentZIzby dokument : dokumenty) {
			String raportDlaHelpDesku = "";
			String raportDlaAdministratora = "";
			
			// Generowanie raportów dla numerów dla których nie ma dokumentów
			if (dokument.getDokumentyZCentralaCntrValidDok() == null && dokument.getDokumentyZCentralaDokumenty() == null && dokument.getDokumentyZIzbyDokumenty() == null) {
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {		
					
					raportDlaHelpDesku += "Szukanego dokumentu o numerze w³asnym " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " brak w bazach centralnej oraz lokalnej.";
					
					raportDlaAdministratora += "Szukany nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " nie wystêpuje w centrali w tabelach cntr_valid_dok, dokumenty oraz lokalnej dokumenty.\n\n";
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					
					raportDlaHelpDesku += "Szukanego dokumentu o numerze systemowym " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " brak w bazach centralnej oraz lokalnej.";
					
					raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " nie wystêpuje w centrali w tabelach cntr_valid_dok, dokumenty oraz lokalnej dokumenty.\n\n";				
				} else {
					
					raportDlaHelpDesku += "Szukanego dokumentu o numerze ewidencyjnym " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " brak w bazach centralnej oraz lokalnej.";	
					
					raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " nie wystêpuje w centrali w tabelach cntr_valid_dok, dokumenty oraz lokalnej dokumenty.\n\n";
				}
				
				raporty.add(new Raport(raportDlaHelpDesku, raportDlaAdministratora));	
			} else if (dokument.getDokumentyZCentralaCntrValidDok() != null && dokument.getDokumentyZCentralaDokumenty() == null && dokument.getDokumentyZIzbyDokumenty() == null) {  //numery s¹ jedynie w cntr_valid_dok
				
				for (DokumentZCentralaCntrValidDok dokumentCntrValidDok : dokument.getDokumentyZCentralaCntrValidDok()) {
					if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
						
						raportDlaHelpDesku += "Szukany dokument o numerze w³asnym " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje siê w bazie centralnej, natomiast brak go w lokalnej"
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? " jednostce " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + "."
										: ".")
								: ".")
							+ " Ma przypisany numer systemowy " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + "."
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + "."
										: ".")
									: "");
						
						raportDlaAdministratora += "Szukany nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje siê w centrali w tabeli cntr_valid_dok, ale brakuje go w centralnej i lokalnej tabeli dokumenty.\n"
							+ ((dokumentCntrValidDok.getIdentyfikatorDokumentu() != null) 
								? "Ma przypisany id_dok " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + ".\n"
								: "")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + ".\n"
										: " - brak opisu dla danego statusu.\n")
								: "")
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? "Jego jedn_przezn to " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
										: " - brak opisu dla danego kodu.\n\n")
								: "");
					} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true ) {
						raportDlaHelpDesku += "Szukany dokument o numerze systemowym " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje siê w bazie centralnej, natomiast brak go w bazach lokalnych."
							+ ((dokumentCntrValidDok.getNumerWlasny() != null) 
								? " Dokument ma nadany numer w³asny " + dokumentCntrValidDok.getNumerWlasny() + "."
								: "")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + "."
										: ".")
								: "")	
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? " Ma przypisan¹ jednostkê przeznaczenia " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + "."
										: ".")
								: "");	
						
						raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje siê w tabeli cntr_valid_dok, ale nie ma go w bazach lokalnych.\n"
							+ ((dokumentCntrValidDok.getNumerWlasny() != null) 
								? "Dokument ma nadany nr_akt " + dokumentCntrValidDok.getNumerWlasny() + ".\n"
								: "")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + ".\n"
										: " - brak opisu dla danego statusu.\n")
								: "")
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? "Ma przypisan¹ jedn_przezn " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
										: " - brak opisu dla danego kodu.\n\n")
								: "");	
					}
					
					raporty.add(new Raport(raportDlaHelpDesku, raportDlaAdministratora));	
				}
			} else if (dokument.getDokumentyZCentralaDokumenty() != null && dokument.getDokumentyZIzbyDokumenty() == null) {  //numer jest w dokumentach
				for (DokumentZCentralaDokumenty dok : dokument.getDokumentyZCentralaDokumenty()) {	
					if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
						
						raportDlaHelpDesku += "Szukany dokument o numerze w³asnym " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje siê w bazie centralnej, natomiast brak go w bazach lokalnych."			
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? " Ma nadany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
								: "")
							+ ((dok.getSymbolDokumentu() != null)
								? " Posiada numer ewidencyjny " + dok.getSymbolDokumentu() + "."
								: "")
							+ ((dok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + "."
									: ".")
								: "")	
							+ ((dok.getIdentyfikatorJednostki() != null)
								? " Ma przypisan¹ jednostkê przeznaczenia " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + "."
									: ".")
								: "");	
				
						raportDlaAdministratora += "Dla szukanego nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znaleziono odpowiadaj¹cy dokument w tabeli dokumenty, ale nie ma go w bazach lokalnych.\n"
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? "Dokument ma nadany id_dok " + dok.getIdentyfikatorDokumentu() + ".\n"
								: "")
							+ ((dok.getSymbolDokumentu() != null) 
								? "Posiada sym_dok " + dok.getSymbolDokumentu() + "\n"
								: "")
							+ ((dok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + ".\n"
									: " - brak opisu dla danego statusu.\n")
								: "")
							+ ((dok.getIdentyfikatorJednostki() != null)
								? "Ma przypisan¹ jedn_przezn " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + ".\n\n"
									: " - brak opisu dla danego kodu.\n\n")
								: "");
					} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {

						raportDlaHelpDesku += "Szukany dokument o numerze systemowym " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje siê w bazie centralnej, natomiast brak go w bazach lokalnych."			
							+ ((dok.getSymbolDokumentu() != null)
								? " Posiada numer ewidencyjny " + dok.getSymbolDokumentu() + "."
								: "")
							+ ((dok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + "."
									: ".")
								: "")
							+ ((dok.getIdentyfikatorJednostki() != null)
								? " Ma przypisan¹ jednostkê przeznaczenia " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + "."
									: ".")
								: "");	
					
						raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje siê w tabeli dokumenty w centrali, ale nie ma go w bazach lokalnych.\n"
							+ ((dok.getSymbolDokumentu() != null) 
								? "Dokument ma nadany sym_dok " + dok.getSymbolDokumentu() + ".\n"
								: "")
							+ ((dok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + ".\n"
									: " - brak opisu dla danego statusu.\n")
								: "")
							+ ((dok.getIdentyfikatorJednostki() != null)
								? "Ma przypisan¹ jedn_przezn " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + ".\n\n"
									: " - brak opisu dla danego kodu.\n\n")
								: "");	
					} else {
						raportDlaHelpDesku += "Szukany dokument o numerze ewidencyjnym " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje siê w bazie centralnej, natomiast brak go w bazach lokalnych."			
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? " Ma nadany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
								: "")
							+ ((dok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + "."
									: ".")
								: "")	
							+ ((dok.getIdentyfikatorJednostki() != null)
								? " Ma przypisan¹ jednostkê przeznaczenia " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + "."
									: ".")
								: "");	
					
						raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje siê w tabeli dokumenty w centrali, ale nie ma go w bazach lokalnych.\n"
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? "Dokument ma nadany id_dok " + dok.getIdentyfikatorDokumentu() + ".\n"
								: "")
							+ ((dok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + ".\n"
									: " - brak opisu dla danego statusu.\n")
								: "")	
							+ ((dok.getIdentyfikatorJednostki() != null)
								? "Ma przypisan¹ jedn_przezn " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + ".\n\n"
									: " - brak opisu dla danego kodu.\n\n")
								: "");
					}
					
					raporty.add(new Raport(raportDlaHelpDesku, raportDlaAdministratora));	
				}
			} else if (dokument.getDokumentyZIzbyDokumenty() != null) { 
				for (DokumentZIzbyDokumenty dok : dokument.getDokumentyZIzbyDokumenty()) {
					if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
		
						raportDlaHelpDesku += "Szukany dokument o numerze w³asnym " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje siê w bazie lokalnej."			
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? " Ma nadany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
								: "")
							+ ((dok.getSymbolDokumentu() != null)
								? " Posiada numer ewidencyjny " + dok.getSymbolDokumentu() + "."
								: "")
							+ ((dok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + "."
									: ".")
								: "")	
							+ ((dok.getIdentyfikatorJednostki() != null)
								? " Ma przypisan¹ jednostkê przeznaczenia " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + "."
									: ".")
								: "");	
				
						raportDlaAdministratora += "Dla szukanego nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znaleziono odpowiadaj¹cy dokument w tabeli dokumenty bazy lokalnej.\n"
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? "Dokument ma nadany id_dok " + dok.getIdentyfikatorDokumentu() + ".\n"
								: "\n")
							+ ((dok.getSymbolDokumentu() != null) 
								? "Posiada sym_dok " + dok.getSymbolDokumentu() + "\n"
								: "\n")
							+ ((dok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + ".\n"
									: " - brak opisu dla danego statusu.\n")
								: "")
							+ ((dok.getIdentyfikatorJednostki() != null)
								? "Ma przypisan¹ jedn_przezn " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + ".\n\n"
									: " - brak opisu dla danego kodu.\n\n")
								: "");
					} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {	
						raportDlaHelpDesku += "Szukany dokument o numerze systemowym " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje siê w bazie lokalnej."			
							+ ((dok.getSymbolDokumentu() != null)
								? " Posiada numer ewidencyjny " + dok.getSymbolDokumentu() + "."
								: "")
							+ ((dok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + "."
									: ".")
								: "")
							+ ((dok.getIdentyfikatorJednostki() != null)
								? " Ma przypisan¹ jednostkê przeznaczenia " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + "."
									: ".")
								: "");	
					
						raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje siê w tabeli dokumenty bazy lokalnej.\n"
							+ ((dok.getSymbolDokumentu() != null) 
								? "Dokument ma nadany sym_dok " + dok.getSymbolDokumentu() + ".\n"
								: "\n")
							+ ((dok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + ".\n"
									: " - brak opisu dla danego statusu.\n")
								: "")
							+ ((dok.getIdentyfikatorJednostki() != null)
								? "Ma przypisan¹ jedn_przezn " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + ".\n\n"
									: " - brak opisu dla danego kodu.\n\n")
								: "");	
					} else {
						raportDlaHelpDesku += "Szukany dokument o numerze ewidencyjnym " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje siê w bazie lokalnej."	
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? " Ma nadany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
								: "")
							+ ((dok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + "."
									: ".")
								: "")	
							+ ((dok.getIdentyfikatorJednostki() != null)
								? " Ma przypisan¹ jednostkê przeznaczenia " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + "."
									: ".")
								: "");	
					
						raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje siê w tabeli dokumenty bazy lokalnej.\n"
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? "Dokument ma nadany id_dok " + dok.getIdentyfikatorDokumentu() + ".\n"
								: "")
							+ ((dok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + ".\n"
									: " - brak opisu dla danego statusu.\n")
								: "")	
							+ ((dok.getIdentyfikatorJednostki() != null)
								? "Ma przypisan¹ jedn_przezn " + dok.getIdentyfikatorJednostki() + ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dok.getIdentyfikatorJednostki()) + ".\n\n"
									: " - brak opisu dla danego kodu.\n\n")
								: "");
					}
					
					raporty.add(new Raport(raportDlaHelpDesku, raportDlaAdministratora));	
				}		
			}
		}
		
		return raporty;	
	}
}
