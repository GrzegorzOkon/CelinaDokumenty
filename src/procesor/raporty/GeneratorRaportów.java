package procesor.raporty;

import java.util.ArrayList;
import java.util.List;

import kontroler.wejscie.Dokument;
import kontroler.wejscie.Identyfikator;
import procesor.dao.entity.DokumentZCentralaCntrValidDok;
import procesor.raporty.wejscie.KodyOddzia³ów;
import procesor.raporty.wejscie.StatusyPrzetwarzania;

public class GeneratorRaportów {
	private static volatile GeneratorRaportów instancja;
	
	private GeneratorRaportów() {	
	}
	
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
	
	public List<String> utwórzRaport(List<Dokument> dokumenty) {		
		String raportDlaHelpDesku = "";
		String raportDlaAdministratora = "";
		List<String> raporty = new ArrayList<>();
		
		for (Dokument dokument : dokumenty) {
			// Generowanie raportu dla numerów dla których nie ma dokumentów
			if (dokument.getDokumentyZCentralaCntrValidDok() == null && dokument.getDokumentZCentralaDokumenty() == null) {
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {				
					raportDlaHelpDesku += "Szukanego numeru w³asnego '" + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
							+ "' brak w bazie centralnej.\n\n";
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					raportDlaHelpDesku += "Szukanego numeru systemowego '" + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) 
							+ "' brak w bazie centralnej.\n\n";
				} else {
					raportDlaHelpDesku += "Szukanego numeru ewidencyjnego '" + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) 
							+ "' brak w bazie centralnej.\n\n";					
				}
			} else if (dokument.getDokumentyZCentralaCntrValidDok() != null && dokument.getDokumentZCentralaDokumenty() == null) {  //numery s¹ jedynie w cntr_valid_dok
				for (DokumentZCentralaCntrValidDok dokumentCntrValidDok : dokument.getDokumentyZCentralaCntrValidDok()) {
					if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
						raportDlaHelpDesku += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true)
							? "numer w³asny '" + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + "'"
							: "")
							+ " znajduje siê w bazie centralnej.\n"
						+ ((dokumentCntrValidDok.getIdentyfikatorDokumentu() != null) 
							? "Dokument ma nadany numer systemowy '" + dokumentCntrValidDok.getIdentyfikatorDokumentu() + "'.\n"
							: "\n")
						+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
							? "Jest w statusie '" + dokumentCntrValidDok.getStatusPrzetwarzania() + "'.\n\n"
							: "");	
					} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
						raportDlaHelpDesku += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true)
								? "numer systemowy '" + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + "'"
								: "")
								+ " znajduje siê w bazie centralnej.\n"
							+ ((dokumentCntrValidDok.getNumerWlasny() != null) 
								? "Dokument ma nadany numer w³asny '" + dokumentCntrValidDok.getNumerWlasny() + "'.\n"
								: "\n")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w statusie '" + dokumentCntrValidDok.getStatusPrzetwarzania() + "'.\n\n"
								: "");							
					}
				}
			} else if (dokument.getDokumentZCentralaDokumenty() != null) {  //numer jest w dokumentach
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
					raportDlaHelpDesku += "Szukany numer w³asny " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
							+ " znajduje siê w bazie centralnej.\n"
						+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() != null) 
							? "Dokument ma nadany numer systemowy " + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() + ".\n"
							: "\n")
						+ ((dokument.getDokumentZCentralaDokumenty().getSymbolDokumentu() != null)
							? "Posiada numer ewidencyjny " + dokument.getDokumentZCentralaDokumenty().getSymbolDokumentu() + ".\n"
							: "\n")
						+ ((dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() != null)
							? "Jest w statusie " + dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()
								+ ((StatusyPrzetwarzania.pobierzInstancjê().pobierzStatus(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzStatus(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()) + ".\n\n"
									: ".\n\n")
							: "");	
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					raportDlaHelpDesku += "Szukany numer systemowy " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) 
							+ " znajduje siê w bazie centralnej.\n"
						+ ((dokument.getDokumentZCentralaDokumenty().getSymbolDokumentu() != null)
							? "Dokument posiada numer ewidencyjny " + dokument.getDokumentZCentralaDokumenty().getSymbolDokumentu() + ".\n"
							: "")
						+ ((dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() != null)
							? "Jest w statusie " + dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()
								+ ((StatusyPrzetwarzania.pobierzInstancjê().pobierzStatus(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzStatus(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()) + ".\n\n"
									: ".\n\n")
							: "");					
				} else {
					raportDlaHelpDesku += "Szukany numer ewidencyjny " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU)
							+ " znajduje siê w bazie centralnej.\n"
						+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() != null) 
							? "Dokument ma nadany numer systemowy " + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() + ".\n"
							: "")
						+ ((dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() != null)
							? "Jest w statusie " + dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()
								+ ((StatusyPrzetwarzania.pobierzInstancjê().pobierzStatus(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzStatus(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()) + ".\n\n"
									: ".\n\n")
							: "");					
				}
			}
		}	
		
		raporty.add(raportDlaHelpDesku);
		raporty.add(raportDlaAdministratora);
		
		return raporty;
	}
}
