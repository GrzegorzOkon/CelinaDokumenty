package procesor.raporty;

import java.util.ArrayList;
import java.util.List;

import kontroler.wejscie.Dokument;
import kontroler.wejscie.Identyfikator;

public class GeneratorRaportów {
	private static volatile GeneratorRaportów instancja;
	
	private GeneratorRaportów() {	
	}
	
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
			// Generowanie raportu dla numerów dla których nie ma dokumentów
			if (dokument.getDokumentyZCentralaCntrValidDok() == null && dokument.getDokumentZCentralaDokumenty() == null) {
				raportDlaHelpDesku += "Szukanego " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) 
						? "numeru własnego dokumentu " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
						: ((dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) 
							? "identyfikatora dokumentu " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) 
							: "")) 
						+ " brak w bazie centralnej.\n\n";
				
				raportDlaAdministratora += "Szukanego " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) 
							? "numeru własnego dokumentu " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
							: ((dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) 
								? "identyfikatora dokumentu " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) 
								: "")) 
							+ " brak w bazie centralnej.\n\n";				
			} else if (dokument.getDokumentyZCentralaCntrValidDok() != null && dokument.getDokumentZCentralaDokumenty() == null) {  //numery są jedynie w cntr_valid_dok
				raportDlaHelpDesku += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true)
							? "numer własny dokumentu " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
							: "")
						+ " znajduje się w bazie centralnej. \n\n";
			} else if (dokument.getDokumentZCentralaDokumenty() != null) {  //numer jest w dokumentach
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
					raportDlaHelpDesku += "Szukany numer własny '" + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + "'" 
							+ " znajduje się w bazie centralnej.\n"
						+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() != null) 
							? "Dokument ma nadany numer systemowy '" + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() + "'.\n"
							: "")
						+ ((dokument.getDokumentZCentralaDokumenty().getSymbolDokumentu() != null)
							? "Posiada numer ewidencyjny '" + dokument.getDokumentZCentralaDokumenty().getSymbolDokumentu() + "'.\n"
							: "")
						+ ((dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() != null)
							? "Jest w statusie '" + dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() + "'.\n\n"
							: "");
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					raportDlaHelpDesku += "Szukany numer systemowy '" + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + "'" 
							+ " znajduje się w bazie centralnej.\n"
						+ ((dokument.getDokumentZCentralaDokumenty().getSymbolDokumentu() != null)
							? "Dokument posiada numer ewidencyjny '" + dokument.getDokumentZCentralaDokumenty().getSymbolDokumentu() + "'.\n"
							: "")
						+ ((dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() != null)
							? "Jest w statusie '" + dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() + "'.\n\n"
							: "");					
				} else {
					raportDlaHelpDesku += "Szukany numer ewidencyjny '" + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + "'" 
							+ " znajduje się w bazie centralnej.\n"
						+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() != null) 
							? "Dokument ma nadany numer systemowy '" + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() + "'.\n"
							: "")
						+ ((dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() != null)
							? "Jest w statusie '" + dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() + "'.\n\n"
							: "");					
				}
			}
		}
		
		raporty.add(raportDlaHelpDesku);
		raporty.add(raportDlaAdministratora);
		
		return raporty;
	}
}
