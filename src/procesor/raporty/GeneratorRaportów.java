package procesor.raporty;

import java.util.ArrayList;
import java.util.List;

import kontroler.wejscie.Dokument;
import kontroler.wejscie.Identyfikator;

public class GeneratorRaport�w {
	private static volatile GeneratorRaport�w instancja;
	
	private GeneratorRaport�w() {	
	}
	
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
	
	public List<String> utw�rzRaport(List<Dokument> dokumenty) {		
		String raportDlaHelpDesku = "";
		String raportDlaAdministratora = "";
		List<String> raporty = new ArrayList<>();
		
		for (Dokument dokument : dokumenty) {
			// Generowanie raportu dla numer�w dla kt�rych nie ma dokument�w
			if (dokument.getDokumentyZCentralaCntrValidDok() == null && dokument.getDokumentZCentralaDokumenty() == null) {
				raportDlaHelpDesku += "Szukanego " 
						+ ((dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) 
								? "numeru w�asnego dokumentu " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
								: ((dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) 
									? "identyfikatora dokumentu " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) 
									: "")) 
						+ " brak w bazie centralnej.\n\n";
				
				raportDlaAdministratora += "Szukanego " 
						+ ((dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) 
								? "numeru w�asnego dokumentu " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
								: ((dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) 
									? "identyfikatora dokumentu " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) 
									: "")) 
						+ " brak w bazie centralnej.\n\n";				
			} else if (dokument.getDokumentyZCentralaCntrValidDok() != null && dokument.getDokumentZCentralaDokumenty() == null) {  //numery s� jedynie w cntr_valid_dok
				raportDlaHelpDesku += "Szukany "
						+ ((dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true)
								? "numer w�asny dokumentu " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
								: "")
						+ " znajduje si� w bazie centralnej. \n\n";
			} else if (dokument.getDokumentZCentralaDokumenty() != null) {  //numer jest w dokumentach
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
					raportDlaHelpDesku += "Szukany numer w�asny dokumentu '" + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + "'" 
								+ " znajduje si� w bazie centralnej.\n"
							+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() != null) 
									? "Ma nadany numer systemowy '" + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() + "'.\n"
									: "")
							+ ((dokument.getDokumentZCentralaDokumenty().getSymbolDokumentu() != null)
									? "Posiada numer ewidencyjny '" + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() + "'.\n"
									: "")
							+ ((dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() != null)
									? "Jest w statusie '" + dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() + "'. "
									: "");
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					
				} else {
					
				}
			}
		}
		
		raporty.add(raportDlaHelpDesku);
		raporty.add(raportDlaAdministratora);
		
		return raporty;
	}
}
