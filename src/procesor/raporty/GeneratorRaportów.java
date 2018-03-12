package procesor.raporty;

import java.util.ArrayList;
import java.util.List;

import kontroler.wejscie.Dokument;
import kontroler.wejscie.Identyfikator;
import procesor.dao.sybase.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.sybase.entity.DokumentZCentralaDokumenty;
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
	
	public List<Raport> utwórzRaport(List<Dokument> dokumenty) {		
		List<Raport> raporty = new ArrayList<>();
		
		for (Dokument dokument : dokumenty) {
			String raportDlaHelpDesku = "";
			String raportDlaAdministratora = "";
			
			// Generowanie raportów dla numerów dla których nie ma dokumentów
			if (dokument.getDokumentyZCentralaCntrValidDok() == null && dokument.getDokumentyZCentralaDokumenty() == null) {
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {				
					raportDlaHelpDesku += "Szukanego numeru w³asnego " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
						+ " brak w bazie centralnej.";
					raportDlaAdministratora += "Szukany nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
						+ " nie wystepuje w tabeli cntr_valid_dok.\n\n";
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					raportDlaHelpDesku += "Szukanego numeru systemowego " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) 
						+ " brak w bazie centralnej.";
					raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) 
						+ " nie wystêpuje w tabelach cntr_valid_dok oraz dokumenty.\n\n";
				} else {
					raportDlaHelpDesku += "Szukanego numeru ewidencyjnego " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) 
						+ " brak w bazie centralnej.";	
					raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) 
						+ " nie wystêpuje w tabeli dokumenty.\n\n";
				}
			} else if (dokument.getDokumentyZCentralaCntrValidDok() != null && dokument.getDokumentyZCentralaDokumenty() == null) {  //numery s¹ jedynie w cntr_valid_dok
				for (DokumentZCentralaCntrValidDok dokumentCntrValidDok : dokument.getDokumentyZCentralaCntrValidDok()) {
					if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
						raportDlaHelpDesku += "Szukany numer w³asny " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje siê w bazie centralnej."
							+ ((dokumentCntrValidDok.getIdentyfikatorDokumentu() != null) 
								? " Dokument ma nadany numer systemowy " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + "."
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
						raportDlaAdministratora += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true)
								? "nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT)
								: "")
							+ " znajduje siê w tabeli cntr_valid_dok.\n"
							+ ((dokumentCntrValidDok.getIdentyfikatorDokumentu() != null) 
								? "Dokument ma nadany id_dok " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + ".\n"
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
						raportDlaHelpDesku += "Szukany numer w³asny " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje siê w bazie centralnej."					
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? " Dokument ma nadany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
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
				
						raportDlaAdministratora += "Dla szukanego nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znaleziono odpowiadaj¹cy dokument w tabeli dokumenty.\n"
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
					}
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					for (DokumentZCentralaDokumenty dok : dokument.getDokumentyZCentralaDokumenty()) {	
						raportDlaHelpDesku += "Szukany numer systemowy " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje siê w bazie centralnej."				
							+ ((dok.getSymbolDokumentu() != null)
								? " Dokument posiada numer ewidencyjny " + dok.getSymbolDokumentu() + "."
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
					
						raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje siê w tabeli dokumenty.\n"
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
					}
				} else {
					for (DokumentZCentralaDokumenty dok : dokument.getDokumentyZCentralaDokumenty()) {	
						raportDlaHelpDesku += "Szukany numer ewidencyjny " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje siê w bazie centralnej."
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? " Dokument ma nadany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
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
					
						raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje siê w tabeli dokumenty.\n"
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
				}
			}
			
			raporty.add(new Raport(raportDlaHelpDesku, raportDlaAdministratora));	
		}
		
		return raporty;
	}
}
