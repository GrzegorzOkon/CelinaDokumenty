package procesor.raporty;

import java.util.ArrayList;
import java.util.List;

import kontroler.wejscie.Dokument;
import kontroler.wejscie.Identyfikator;
import procesor.dao.sybase.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.sybase.entity.DokumentZCentralaDokumenty;
import procesor.raporty.wejscie.KodyOddzia��w;
import procesor.raporty.wejscie.Raport;
import procesor.raporty.wejscie.StatusyPrzetwarzania;
import procesor.raporty.wejscie.Tabela;

public class GeneratorRaport�w {
	private static volatile GeneratorRaport�w instancja;
	
	private GeneratorRaport�w() {}
	
	public static GeneratorRaport�w pobierzInstancj�() {
		if (instancja == null) {
			synchronized(GeneratorRaport�w.class) {
				if(instancja == null) {
					instancja = new GeneratorRaport�w();
				}
			}
		} 		
		return instancja;
	}
	
	public List<Raport> utw�rzRaport(List<Dokument> dokumenty) {		
		List<Raport> raporty = new ArrayList<>();
		
		for (Dokument dokument : dokumenty) {
			String raportDlaHelpDesku = "";
			String raportDlaAdministratora = "";
			
			// Generowanie raport�w dla numer�w dla kt�rych nie ma dokument�w
			if (dokument.getDokumentyZCentralaCntrValidDok() == null && dokument.getDokumentyZCentralaDokumenty() == null) {
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {				
					raportDlaHelpDesku += "Szukanego numeru w�asnego " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
						+ " brak w bazie centralnej.";
					raportDlaAdministratora += "Szukany nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
						+ " nie wystepuje w tabeli cntr_valid_dok.\n\n";
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					raportDlaHelpDesku += "Szukanego numeru systemowego " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) 
						+ " brak w bazie centralnej.";
					raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) 
						+ " nie wyst�puje w tabelach cntr_valid_dok oraz dokumenty.\n\n";
				} else {
					raportDlaHelpDesku += "Szukanego numeru ewidencyjnego " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) 
						+ " brak w bazie centralnej.";	
					raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) 
						+ " nie wyst�puje w tabeli dokumenty.\n\n";
				}
			} else if (dokument.getDokumentyZCentralaCntrValidDok() != null && dokument.getDokumentyZCentralaDokumenty() == null) {  //numery s� jedynie w cntr_valid_dok
				for (DokumentZCentralaCntrValidDok dokumentCntrValidDok : dokument.getDokumentyZCentralaCntrValidDok()) {
					if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
						raportDlaHelpDesku += "Szukany numer w�asny " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje si� w bazie centralnej."
							+ ((dokumentCntrValidDok.getIdentyfikatorDokumentu() != null) 
								? " Dokument ma nadany numer systemowy " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + "."
								: "")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + "."
										: ".")
								: "")	
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? " Ma przypisan� jednostk� przeznaczenia " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + "."
										: ".")
								: "");	
						raportDlaAdministratora += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true)
								? "nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT)
								: "")
							+ " znajduje si� w tabeli cntr_valid_dok.\n"
							+ ((dokumentCntrValidDok.getIdentyfikatorDokumentu() != null) 
								? "Dokument ma nadany id_dok " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + ".\n"
								: "\n")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + ".\n"
										: " - brak opisu dla danego statusu.\n")
								: "")
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? "Ma przypisan� jedn_przezn " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
										: " - brak opisu dla danego kodu.\n\n")
								: "");
					} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
						raportDlaHelpDesku += "Szukany numer systemowy " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje si� w bazie centralnej."
							+ ((dokumentCntrValidDok.getNumerWlasny() != null) 
								? " Dokument ma nadany numer w�asny " + dokumentCntrValidDok.getNumerWlasny() + "."
								: "")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + "."
										: ".")
								: "")	
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? " Ma przypisan� jednostk� przeznaczenia " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + "."
										: ".")
								: "");	
						
						raportDlaAdministratora += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true)
								? "id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU)
								: "")
								+ " znajduje si� w tabeli cntr_valid_dok.\n"
							+ ((dokumentCntrValidDok.getNumerWlasny() != null) 
								? "Dokument ma nadany nr_akt " + dokumentCntrValidDok.getNumerWlasny() + ".\n"
								: "\n")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + ".\n"
										: " - brak opisu dla danego statusu.\n")
								: "")
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? "Ma przypisan� jedn_przezn " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
										: " - brak opisu dla danego kodu.\n\n")
								: "");	
					}
				}
			} else if (dokument.getDokumentyZCentralaDokumenty() != null) {  //numer jest w dokumentach
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
					for (DokumentZCentralaDokumenty dok : dokument.getDokumentyZCentralaDokumenty()) {		
						raportDlaHelpDesku += "Szukany numer w�asny " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje si� w bazie centralnej."					
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? " Dokument ma nadany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
								: "")
							+ ((dok.getSymbolDokumentu() != null)
								? " Posiada numer ewidencyjny " + dok.getSymbolDokumentu() + "."
								: "")
							+ ((dok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + "."
									: ".")
								: "")	
							+ ((dok.getIdentyfikatorJednostki() != null)
								? " Ma przypisan� jednostk� przeznaczenia " + dok.getIdentyfikatorJednostki() + ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) + "."
									: ".")
								: "");	
				
						raportDlaAdministratora += "Dla szukanego nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znaleziono odpowiadaj�cy dokument w tabeli dokumenty.\n"
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? "Dokument ma nadany id_dok " + dok.getIdentyfikatorDokumentu() + ".\n"
								: "\n")
							+ ((dok.getSymbolDokumentu() != null) 
								? "Posiada sym_dok " + dok.getSymbolDokumentu() + "\n"
								: "\n")
							+ ((dok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + ".\n"
									: " - brak opisu dla danego statusu.\n")
								: "")
							+ ((dok.getIdentyfikatorJednostki() != null)
								? "Ma przypisan� jedn_przezn " + dok.getIdentyfikatorJednostki() + ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) + ".\n\n"
									: " - brak opisu dla danego kodu.\n\n")
								: "");
					}
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					for (DokumentZCentralaDokumenty dok : dokument.getDokumentyZCentralaDokumenty()) {	
						raportDlaHelpDesku += "Szukany numer systemowy " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje si� w bazie centralnej."				
							+ ((dok.getSymbolDokumentu() != null)
								? " Dokument posiada numer ewidencyjny " + dok.getSymbolDokumentu() + "."
								: "")
							+ ((dok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + "."
									: ".")
								: "")
							+ ((dok.getIdentyfikatorJednostki() != null)
								? " Ma przypisan� jednostk� przeznaczenia " + dok.getIdentyfikatorJednostki() + ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) + "."
									: ".")
								: "");	
					
						raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje si� w tabeli dokumenty.\n"
							+ ((dok.getSymbolDokumentu() != null) 
								? "Dokument ma nadany sym_dok " + dok.getSymbolDokumentu() + ".\n"
								: "\n")
							+ ((dok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + ".\n"
									: " - brak opisu dla danego statusu.\n")
								: "")
							+ ((dok.getIdentyfikatorJednostki() != null)
								? "Ma przypisan� jedn_przezn " + dok.getIdentyfikatorJednostki() + ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) + ".\n\n"
									: " - brak opisu dla danego kodu.\n\n")
								: "");	
					}
				} else {
					for (DokumentZCentralaDokumenty dok : dokument.getDokumentyZCentralaDokumenty()) {	
						raportDlaHelpDesku += "Szukany numer ewidencyjny " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje si� w bazie centralnej."
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? " Dokument ma nadany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
								: "")
							+ ((dok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + "."
									: ".")
								: "")	
							+ ((dok.getIdentyfikatorJednostki() != null)
								? " Ma przypisan� jednostk� przeznaczenia " + dok.getIdentyfikatorJednostki() + ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) + "."
									: ".")
								: "");	
					
						raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje si� w tabeli dokumenty.\n"
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? "Dokument ma nadany id_dok " + dok.getIdentyfikatorDokumentu() + ".\n"
								: "")
							+ ((dok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + ".\n"
									: " - brak opisu dla danego statusu.\n")
								: "")	
							+ ((dok.getIdentyfikatorJednostki() != null)
								? "Ma przypisan� jedn_przezn " + dok.getIdentyfikatorJednostki() + ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) + ".\n\n"
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
