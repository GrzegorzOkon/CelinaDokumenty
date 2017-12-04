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
			// Generowanie raportów dla numerów dla których nie ma dokumentów
			if (dokument.getDokumentyZCentralaCntrValidDok() == null && dokument.getDokumentZCentralaDokumenty() == null) {
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {				
					raportDlaHelpDesku += "Szukanego numeru w³asnego " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
						+ " brak w bazie centralnej.\n\n";
					raportDlaAdministratora += "Szukany nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
						+ " nie wystepuje w tabeli cntr_valid_dok.\n\n";
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					raportDlaHelpDesku += "Szukanego numeru systemowego " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) 
						+ " brak w bazie centralnej.\n\n";
					raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) 
						+ " nie wystêpuje w tabelach cntr_valid_dok oraz dokumenty\n\n";
				} else {
					raportDlaHelpDesku += "Szukanego numeru ewidencyjnego " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) 
						+ " brak w bazie centralnej.\n\n";	
					raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) 
						+ " nie wystêpuje w tabeli dokumenty.\n\n";
				}
			} else if (dokument.getDokumentyZCentralaCntrValidDok() != null && dokument.getDokumentZCentralaDokumenty() == null) {  //numery s¹ jedynie w cntr_valid_dok
				for (DokumentZCentralaCntrValidDok dokumentCntrValidDok : dokument.getDokumentyZCentralaCntrValidDok()) {
					if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
						raportDlaHelpDesku += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true)
								? "numer w³asny " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT)
								: "")
							+ " znajduje siê w bazie centralnej.\n"
							+ ((dokumentCntrValidDok.getIdentyfikatorDokumentu() != null) 
								? "Dokument ma nadany numer systemowy " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + ".\n"
								: "\n")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w statusie " + dokumentCntrValidDok.getStatusPrzetwarzania() + ".\n"
								: "")
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? "Ma przypisany kod oddzia³u " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
										: ".\n\n")
								: "");	
						raportDlaAdministratora += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true)
								? "nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT)
								: "")
							+ " znajduje siê w tabeli cntr_valid_dok.\n"
							+ ((dokumentCntrValidDok.getIdentyfikatorDokumentu() != null) 
								? "Dokument ma nadany id_dok " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + ".\n"
								: "\n")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jego status_przetw to " + dokumentCntrValidDok.getStatusPrzetwarzania() + ".\n"
								: "")
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? "Ma przypisan¹ jedn_przezn " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
										: " - brak opisu dla danego kodu.\n\n")
								: "");
					} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
						raportDlaHelpDesku += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true)
								? "numer systemowy " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU)
								: "")
								+ " znajduje siê w bazie centralnej.\n"
							+ ((dokumentCntrValidDok.getNumerWlasny() != null) 
								? "Dokument ma nadany numer w³asny " + dokumentCntrValidDok.getNumerWlasny() + ".\n"
								: "\n")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w statusie " + dokumentCntrValidDok.getStatusPrzetwarzania() + ".\n"
								: "")	
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? "Zosta³ wys³any na placówkê " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
										: ".\n\n")
								: "");	
					}
				}
			} else if (dokument.getDokumentZCentralaDokumenty() != null) {  //numer jest w dokumentach
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
					for (DokumentZCentralaCntrValidDok dokumentCntrValidDok : dokument.getDokumentyZCentralaCntrValidDok()) {
						raportDlaHelpDesku += "Szukany " + ((dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true)
							? "numer w³asny " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT)
							: "")
							+ " znajduje siê w bazie centralnej.\n"
						+ ((dokumentCntrValidDok.getIdentyfikatorDokumentu() != null) 
							? "Dokument ma nadany numer systemowy " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + ".\n"
							: "\n")
						+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
							? "Jest w statusie " + dokumentCntrValidDok.getStatusPrzetwarzania() + ".\n"
							: "")
						+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
							? "Zosta³ wys³any na placówkê " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
								+ ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
									: ".\n\n")
							: "");	
					}
					/*raportDlaHelpDesku += "Szukany numer w³asny " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) 
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
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzStatus(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()) + ".\n"
									: ".\n")
							: "")
						+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki() != null)
							? "Zosta³ wys³any na placówkê " + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()
								+ ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) + ".\n\n"
									: ".\n\n")
							: "");	*/
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					raportDlaHelpDesku += "Szukany numer systemowy " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) 
							+ " znajduje siê w bazie centralnej.\n"
						+ ((dokument.getDokumentZCentralaDokumenty().getSymbolDokumentu() != null)
							? "Dokument posiada numer ewidencyjny " + dokument.getDokumentZCentralaDokumenty().getSymbolDokumentu() + ".\n"
							: "")
						+ ((dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania() != null)
							? "Jest w statusie " + dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()
								+ ((StatusyPrzetwarzania.pobierzInstancjê().pobierzStatus(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()) != null)
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzStatus(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()) + ".\n"
									: ".\n")
							: "")
						+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki() != null)
							? "Zosta³ wys³any na placówkê " + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()
								+ ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) + ".\n\n"
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
									? " - " + StatusyPrzetwarzania.pobierzInstancjê().pobierzStatus(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorRodzajuDokumentu(), dokument.getDokumentZCentralaDokumenty().getStatusPrzetwarzania()) + ".\n"
									: ".\n")
							: "")	
						+ ((dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki() != null)
							? "Zosta³ wys³any na placówkê " + dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()
								+ ((KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) != null)
									? " - " + KodyOddzia³ów.pobierzInstancjê().pobierzKod(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorJednostki()) + ".\n\n"
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
