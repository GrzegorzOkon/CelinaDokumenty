package procesor.raporty;

import java.util.ArrayList;
import java.util.List;

import kontroler.wejscie.Dokument;
import kontroler.wejscie.Identyfikator;
import procesor.dao.entity.DokumentZCentralaCntrValidDok;
import procesor.raporty.wejscie.KodyOddziałów;
import procesor.raporty.wejscie.StatusyPrzetwarzania;

public class GeneratorRaportów {
	private static volatile GeneratorRaportów instancja;
	
	private GeneratorRaportów() {}
	
	public static GeneratorRaportów pobierzInstancję() {
		if (instancja == null) {
			synchronized(GeneratorRaportów.class) {
				if(instancja == null) {
					instancja = new GeneratorRaportów();
				}
			}
		} 		
		return instancja;
	}
	
	public List<String> utwórzRaport(List<Dokument> dokumenty) {		
		String raportDlaHelpDesku = "";
		String raportDlaAdministratora = "";
		List<String> raporty = new ArrayList<>();
		
		for (Dokument dokument : dokumenty) {
			// Generowanie raportów dla numerów dla których nie ma dokumentów
			if (dokument.getDokumentyZCentralaCntrValidDok() == null && dokument.getDokumentZCentralaDokumenty() == null) {
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {				
					raportDlaHelpDesku += "Szukanego numeru własnego " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
						+ " brak w bazie centralnej.\n\n";
					raportDlaAdministratora += "Szukany nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
						+ " nie wystepuje w tabeli cntr_valid_dok.\n\n";
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					raportDlaHelpDesku += "Szukanego numeru systemowego " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) 
						+ " brak w bazie centralnej.\n\n";
					raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) 
						+ " nie występuje w tabelach cntr_valid_dok oraz dokumenty\n\n";
				} else {
					raportDlaHelpDesku += "Szukanego numeru ewidencyjnego " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) 
						+ " brak w bazie centralnej.\n\n";	
					raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) 
						+ " nie występuje w tabeli dokumenty.\n\n";
				}
			} else if (dokument.getDokumentyZCentralaCntrValidDok() != null && dokument.getDokumentZCentralaDokumenty() == null) {  //numery są jedynie w cntr_valid_dok
				for (DokumentZCentralaCntrValidDok dokumentCntrValidDok : dokument.getDokumentyZCentralaCntrValidDok()) {
					if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
						raportDlaHelpDesku += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true)
								? "numer własny " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT)
								: "")
							+ " znajduje się w bazie centralnej.\n"
							+ ((dokumentCntrValidDok.getIdentyfikatorDokumentu() != null) 
								? "Dokument ma nadany numer systemowy " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + ".\n"
								: "\n")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w statusie " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), "cntr_valid_dok") != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), "cntr_valid_dok") + ".\n"
										: ".\n")
								: "")	
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? "Ma przypisaną jednostkę przeznaczenia " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddziałów.pobierzInstancję().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddziałów.pobierzInstancję().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
										: ".\n\n")
								: "");	
						raportDlaAdministratora += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true)
								? "nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT)
								: "")
							+ " znajduje się w tabeli cntr_valid_dok.\n"
							+ ((dokumentCntrValidDok.getIdentyfikatorDokumentu() != null) 
								? "Dokument ma nadany id_dok " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + ".\n"
								: "\n")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), "cntr_valid_dok") != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), "cntr_valid_dok") + ".\n"
										: ".\n")
								: "")
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? "Ma przypisaną jedn_przezn " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddziałów.pobierzInstancję().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddziałów.pobierzInstancję().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
										: " - brak opisu dla danego kodu.\n\n")
								: "");
					} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
						raportDlaHelpDesku += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true)
								? "numer systemowy " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU)
								: "")
							+ " znajduje się w bazie centralnej.\n"
							+ ((dokumentCntrValidDok.getNumerWlasny() != null) 
								? "Dokument ma nadany numer własny " + dokumentCntrValidDok.getNumerWlasny() + ".\n"
								: "\n")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w statusie " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), "cntr_valid_dok") != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), "cntr_valid_dok") + ".\n"
										: ".\n")
								: "")	
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? "Ma przypisaną jednostkę przeznaczenia " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddziałów.pobierzInstancję().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddziałów.pobierzInstancję().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
										: ".\n\n")
								: "");	
						
						raportDlaAdministratora += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true)
								? "id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU)
								: "")
								+ " znajduje się w tabeli cntr_valid_dok.\n"
							+ ((dokumentCntrValidDok.getNumerWlasny() != null) 
								? "Dokument ma nadany nr_akt " + dokumentCntrValidDok.getNumerWlasny() + ".\n"
								: "\n")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), "cntr_valid_dok") != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), "cntr_valid_dok") + ".\n"
										: ".\n")
								: "")
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? "Ma przypisaną jedn_przezn " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddziałów.pobierzInstancję().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddziałów.pobierzInstancję().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
										: " - brak opisu dla danego kodu.\n\n")
								: "");	
					}
				}
			} else if (dokument.getDokumentZCentralaDokumenty() != null) {  //numer jest w dokumentach
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
					raportDlaHelpDesku += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true)
							? "numer własny " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT)
							: "")
							+ " znajduje się w bazie centralnej.\n"
						+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() != null) 
							? "Dokument ma nadany numer systemowy " + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() + ".\n"
							: "\n")
						+ ((dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() != null)
							? "Jest w statusie " + dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()
								+ ((StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania(), "dokumenty") != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania(), "dokumenty") + ".\n"
									: ".\n")
							: "")	
						+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki() != null)
							? "Ma przypisaną jednostkę przeznaczenia " + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()
								+ ((KodyOddziałów.pobierzInstancję().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddziałów.pobierzInstancję().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) + ".\n\n"
									: ".\n\n")
							: "");	
				
					for (DokumentZCentralaCntrValidDok dokumentZCentralaCntrValidDok : dokument.getDokumentyZCentralaCntrValidDok()) {  //wyświetla wszystkie znalezione dokumenty z tabeli cntr_valid_dok
						raportDlaAdministratora += "Szukany nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
							+ " znajduje się w tabeli cntr_valid_dok.\n"
							+ ((dokumentZCentralaCntrValidDok.getIdentyfikatorDokumentu() != null) 
								? "Dokument ma nadany id_dok " + dokumentZCentralaCntrValidDok.getIdentyfikatorDokumentu() + ".\n"
								: "\n")
							+ ((dokumentZCentralaCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dokumentZCentralaCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokumentZCentralaCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentZCentralaCntrValidDok.getStatusPrzetwarzania(), "cntr_valid_dok") != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokumentZCentralaCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentZCentralaCntrValidDok.getStatusPrzetwarzania(), "cntr_valid_dok") + ".\n"
										: ".\n")
								: "")
							+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki() != null)
								? "Ma przypisaną jedn_przezn " + dokumentZCentralaCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddziałów.pobierzInstancję().pobierzKod(dokumentZCentralaCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddziałów.pobierzInstancję().pobierzKod(dokumentZCentralaCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
										: " - brak opisu dla danego kodu.\n\n")
								: "");	
					}				
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					raportDlaHelpDesku += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true)
							? "numer systemowy " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU)
							: "")
							+ " znajduje się w bazie centralnej.\n"
						+ ((dokument.getDokumentZCentralaDokumenty().getSymbolDokumentu() != null)
							? "Dokument posiada numer ewidencyjny " + dokument.getDokumentZCentralaDokumenty().getSymbolDokumentu() + ".\n"
							: "\n")
						+ ((dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() != null)
							? "Jest w statusie " + dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()
								+ ((StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania(), "dokumenty") != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania(), "dokumenty") + ".\n"
									: ".\n")
							: "")
						+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki() != null)
							? "Ma przypisaną jednostkę przeznaczenia " + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()
								+ ((KodyOddziałów.pobierzInstancję().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddziałów.pobierzInstancję().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) + ".\n\n"
									: ".\n\n")
							: "");	
					
					raportDlaAdministratora += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true)
							? "id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU)
							: "")
							+ " znajduje się w tabeli dokumenty.\n"
						+ ((dokument.getDokumentZCentralaDokumenty().getSymbolDokumentu() != null) 
							? "Dokument ma nadany sym_dok " + dokument.getDokumentZCentralaDokumenty().getSymbolDokumentu() + ".\n"
							: "\n")
						+ ((dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() != null)
							? "Jest w status_przetw " + dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()
								+ ((StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania(), "dokumenty") != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania(), "dokumenty") + ".\n"
									: ".\n")
							: "")
						+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki() != null)
							? "Ma przypisaną jedn_przezn " + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()
								+ ((KodyOddziałów.pobierzInstancję().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddziałów.pobierzInstancję().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) + ".\n\n"
									: " - brak opisu dla danego kodu.\n\n")
							: "");		
				} else {
					raportDlaHelpDesku += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.SYMBOL_DOKUMENTU) == true)
							? "numer ewidencyjny " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU)
							: "")
							+ " znajduje się w bazie centralnej.\n"
						+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() != null) 
							? "Dokument ma nadany numer systemowy " + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() + ".\n"
							: "")
						+ ((dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() != null)
							? "Jest w statusie " + dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()
								+ ((StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania(), "dokumenty") != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania(), "dokumenty") + ".\n"
									: ".\n")
							: "")	
						+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki() != null)
							? "Ma przypisaną jednostkę przeznaczenia " + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()
								+ ((KodyOddziałów.pobierzInstancję().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddziałów.pobierzInstancję().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) + ".\n\n"
									: ".\n\n")
							: "");	
					
					raportDlaAdministratora += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.SYMBOL_DOKUMENTU) == true)
							? "sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU)
							: "")
							+ " znajduje się w tabeli dokumenty.\n"
						+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() != null) 
							? "Dokument ma nadany id_dok " + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() + ".\n"
							: "")
						+ ((dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() != null)
							? "Jest w status_przetw " + dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()
								+ ((StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania(), "dokumenty") != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancję().pobierzOpisStatusu(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania(), "dokumenty") + ".\n"
									: ".\n")
							: "")	
						+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki() != null)
							? "Ma przypisaną jedn_przezn " + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()
								+ ((KodyOddziałów.pobierzInstancję().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddziałów.pobierzInstancję().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) + ".\n\n"
									: " - brak opisu dla danego kodu.\n\n")
							: "");
				}
			}
		}	
		
		raporty.add(raportDlaHelpDesku);
		raporty.add(raportDlaAdministratora);
		
		return raporty;
	}
}
