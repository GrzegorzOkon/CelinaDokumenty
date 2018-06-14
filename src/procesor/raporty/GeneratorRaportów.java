package procesor.raporty;

import java.util.ArrayList;
import java.util.List;

import kontroler.wejscie.Dokument;
import kontroler.wejscie.DokumentZIzby;
import kontroler.wejscie.Identyfikator;
import procesor.dao.sybase.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.sybase.entity.DokumentZCentralaDokumenty;
import procesor.dao.sybase.entity.DokumentZIzbyDokumenty;
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
	
	public List<Raport> utw�rzCentralnyRaport(List<Dokument> dokumenty) {		
		List<Raport> raporty = new ArrayList<>();
		
		for (Dokument dokument : dokumenty) {
			String raportDlaHelpDesku = "";
			String raportDlaAdministratora = "";
			
			// Generowanie raport�w dla numer�w dla kt�rych nie ma dokument�w
			if (dokument.getDokumentyZCentralaCntrValidDok() == null && dokument.getDokumentyZCentralaDokumenty() == null) {
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {	
					
					raportDlaHelpDesku += "Szukanego dokumentu o numerze w�asnym " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " brak w bazie centralnej.";
					
					raportDlaAdministratora += "Szukany nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " nie wystepuje w tabeli cntr_valid_dok.\n\n";
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					
					raportDlaHelpDesku += "Szukanego dokumentu o numerze systemowym " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " brak w bazie centralnej.";
					
					raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " nie wyst�puje w tabelach cntr_valid_dok oraz dokumenty.\n\n";
				} else {
					
					raportDlaHelpDesku += "Szukanego dokumentu o numerze ewidencyjnym " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " brak w bazie centralnej.";	
					
					raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " nie wyst�puje w tabeli dokumenty.\n\n";
				}
			} else if (dokument.getDokumentyZCentralaCntrValidDok() != null && dokument.getDokumentyZCentralaDokumenty() == null) {  //numery s� jedynie w cntr_valid_dok
				for (DokumentZCentralaCntrValidDok dokumentCntrValidDok : dokument.getDokumentyZCentralaCntrValidDok()) {
					if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
						
						raportDlaHelpDesku += "Szukany dokument o numerze w�asnym " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje si� w bazie centralnej."
							+ " Ma przypisany numer systemowy " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + "."
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + "."
										: ".")
								: "")	
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? " Jego jednostka przeznaczenia to " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + "."
										: ".")
								: "");	
						
						raportDlaAdministratora += "Szukany nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje si� w tabeli cntr_valid_dok, ale brak go w tabeli dokumenty.\n"
							+ ((dokumentCntrValidDok.getIdentyfikatorDokumentu() != null) 
								? "Ma przypisany id_dok " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + ".\n"
								: "")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + ".\n"
										: " - brak opisu dla danego statusu.\n")
								: "")
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? "Jego jedn_przezn to " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
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
						
						raportDlaHelpDesku += "Szukany dokument o numerze w�asnym " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje si� w bazie centralnej."					
							+ " Ma przypisany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
							+ ((dok.getSymbolDokumentu() != null)
								? " Posiada numer ewidencyjny " + dok.getSymbolDokumentu() + "."
								: "")
							+ " Jest w statusie " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
								? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + "."
								: ".")	
							+ " Jego jednostka przeznaczenia to " + dok.getIdentyfikatorJednostki() + ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
								? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) + "."
								: ".");	
				
						raportDlaAdministratora += "Dla szukanego nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znaleziono odpowiadaj�cy dokument w tabeli dokumenty.\n"
							+ "Ma przypisany id_dok " + dok.getIdentyfikatorDokumentu() + ".\n"
							+ ((dok.getSymbolDokumentu() != null) 
								? "Posiada sym_dok " + dok.getSymbolDokumentu() + "\n"
								: "")
							+ "Jest w status_przetw " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
								? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + ".\n"
								: " - brak opisu dla danego statusu.\n")
							+ "Jego jedn_przezn to " + dok.getIdentyfikatorJednostki() + ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
								? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) + ".\n\n"
								: " - brak opisu dla danego kodu.\n\n");
					}
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {			
					for (DokumentZCentralaDokumenty dok : dokument.getDokumentyZCentralaDokumenty()) {	
						
						raportDlaHelpDesku += "Szukany dokument o numerze systemowym " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje si� w bazie centralnej."				
							+ ((dok.getSymbolDokumentu() != null) 
								? " Posiada numer ewidencyjny " + dok.getSymbolDokumentu() + "." 
								: "")
							+ " Jest w statusie " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
								? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + "."
								: ".")
							+ " Jego jednostka przeznaczenia to " + dok.getIdentyfikatorJednostki() + ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
								? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) + "."
								: ".");	
					
						raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje si� w tabeli dokumenty.\n"
							+ ((dok.getSymbolDokumentu() != null) 
								? "Posiada sym_dok " + dok.getSymbolDokumentu() + ".\n" 
								: "Nie posiada sym_dok.\n")
							+ "Jest w status_przetw " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
								? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + ".\n"
								: " - brak opisu dla danego statusu.\n")
							+ "Jego id_jedn to " + dok.getIdentyfikatorJednostki() + ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
								? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) + ".\n\n"
								: " - brak opisu dla danego kodu.\n\n");	
					}
				} else {
					for (DokumentZCentralaDokumenty dok : dokument.getDokumentyZCentralaDokumenty()) {	
						
						raportDlaHelpDesku += "Szukany dokument o numerze ewidencyjnym " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje si� w bazie centralnej."
							+ " Ma przypisany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
							+ " Jest w statusie " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
								? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + "."
								: ".")	
							+ "Jego jednostka przeznaczenia to " + dok.getIdentyfikatorJednostki() + ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
								? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) + "."
								: ".");	
					
						raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje si� w tabeli dokumenty.\n"
							+ "Ma przypisany id_dok " + dok.getIdentyfikatorDokumentu() + ".\n"
							+ "Jest w status_przetw " + dok.getStatusPrzetwarzania() + ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) != null)
								? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dok.getIdentyfikatorRodzajuDokumentu(), dok.getStatusPrzetwarzania(), Tabela.DOKUMENTY) + ".\n"
								: " - brak opisu dla danego statusu.\n")	
							+ "Jego id_jedn to " + dok.getIdentyfikatorJednostki() + ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) != null)
								? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dok.getIdentyfikatorJednostki()) + ".\n\n"
								: " - brak opisu dla danego kodu.\n\n");
					}
				}
			}
			
			raporty.add(new Raport(raportDlaHelpDesku, raportDlaAdministratora));	
		}
		
		return raporty;
	}
	
	
	public List<Raport> utw�rzLokalnyRaport(List<DokumentZIzby> dokumenty) {		
		List<Raport> raporty = new ArrayList<>();
		
		for (DokumentZIzby dokument : dokumenty) {
			String raportDlaHelpDesku = "";
			String raportDlaAdministratora = "";
			
			// Generowanie raport�w dla numer�w dla kt�rych nie ma dokument�w
			if (dokument.getDokumentyZCentralaCntrValidDok() == null && dokument.getDokumentyZCentralaDokumenty() == null && dokument.getDokumentyZIzbyDokumenty() == null) {
				if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {		
					
					raportDlaHelpDesku += "Szukanego dokumentu o numerze w�asnym " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " brak w bazach centralnej oraz lokalnej.";
					
					raportDlaAdministratora += "Szukany nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " nie wyst�puje w centrali w tabelach cntr_valid_dok, dokumenty oraz lokalnej dokumenty.\n\n";
				} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {
					
					raportDlaHelpDesku += "Szukanego dokumentu o numerze systemowym " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " brak w bazach centralnej oraz lokalnej.";
					
					raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " nie wyst�puje w centrali w tabelach cntr_valid_dok, dokumenty oraz lokalnej dokumenty.\n\n";				
				} else {
					
					raportDlaHelpDesku += "Szukanego dokumentu o numerze ewidencyjnym " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " brak w bazach centralnej oraz lokalnej.";	
					
					raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " nie wyst�puje w centrali w tabelach cntr_valid_dok, dokumenty oraz lokalnej dokumenty.\n\n";
				}
				
				raporty.add(new Raport(raportDlaHelpDesku, raportDlaAdministratora));	
			} else if (dokument.getDokumentyZCentralaCntrValidDok() != null && dokument.getDokumentyZCentralaDokumenty() == null && dokument.getDokumentyZIzbyDokumenty() == null) {  //numery s� jedynie w cntr_valid_dok
				
				for (DokumentZCentralaCntrValidDok dokumentCntrValidDok : dokument.getDokumentyZCentralaCntrValidDok()) {
					if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
						
						raportDlaHelpDesku += "Szukany dokument o numerze w�asnym " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje si� w bazie centralnej, natomiast brak go w lokalnej"
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? " jednostce " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + "."
										: ".")
								: ".")
							+ " Ma przypisany numer systemowy " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + "."
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? " Jest w statusie " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + "."
										: ".")
									: "");
						
						raportDlaAdministratora += "Szukany nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje si� w centrali w tabeli cntr_valid_dok, ale brakuje go w centralnej i lokalnej tabeli dokumenty.\n"
							+ ((dokumentCntrValidDok.getIdentyfikatorDokumentu() != null) 
								? "Ma przypisany id_dok " + dokumentCntrValidDok.getIdentyfikatorDokumentu() + ".\n"
								: "")
							+ ((dokumentCntrValidDok.getStatusPrzetwarzania() != null)
								? "Jest w status_przetw " + dokumentCntrValidDok.getStatusPrzetwarzania()
									+ ((StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) != null)
										? " - " + StatusyPrzetwarzania.pobierzInstancj�().pobierzOpisStatusu(dokumentCntrValidDok.getIdentyfikatorRodzajuDokumentu(), dokumentCntrValidDok.getStatusPrzetwarzania(), Tabela.CNTR_VALID_DOK) + ".\n"
										: " - brak opisu dla danego statusu.\n")
								: "")
							+ ((dokumentCntrValidDok.getJednostkaPrzeznaczenia() != null)
								? "Jego jedn_przezn to " + dokumentCntrValidDok.getJednostkaPrzeznaczenia()
									+ ((KodyOddzia��w.pobierzInstancj�().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) != null)
										? " - " + KodyOddzia��w.pobierzInstancj�().pobierzKod(dokumentCntrValidDok.getJednostkaPrzeznaczenia()) + ".\n\n"
										: " - brak opisu dla danego kodu.\n\n")
								: "");
					} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true ) {
						raportDlaHelpDesku += "Szukany dokument o numerze systemowym " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje si� w bazie centralnej, natomiast brak go w bazach lokalnych."
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
						
						raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje si� w tabeli cntr_valid_dok, ale nie ma go w bazach lokalnych.\n"
							+ ((dokumentCntrValidDok.getNumerWlasny() != null) 
								? "Dokument ma nadany nr_akt " + dokumentCntrValidDok.getNumerWlasny() + ".\n"
								: "")
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
					
					raporty.add(new Raport(raportDlaHelpDesku, raportDlaAdministratora));	
				}
			} else if (dokument.getDokumentyZCentralaDokumenty() != null && dokument.getDokumentyZIzbyDokumenty() == null) {  //numer jest w dokumentach
				for (DokumentZCentralaDokumenty dok : dokument.getDokumentyZCentralaDokumenty()) {	
					if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
						
						raportDlaHelpDesku += "Szukany dokument o numerze w�asnym " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje si� w bazie centralnej, natomiast brak go w bazach lokalnych."			
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? " Ma nadany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
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
				
						raportDlaAdministratora += "Dla szukanego nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znaleziono odpowiadaj�cy dokument w tabeli dokumenty, ale nie ma go w bazach lokalnych.\n"
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? "Dokument ma nadany id_dok " + dok.getIdentyfikatorDokumentu() + ".\n"
								: "")
							+ ((dok.getSymbolDokumentu() != null) 
								? "Posiada sym_dok " + dok.getSymbolDokumentu() + "\n"
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
					} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {

						raportDlaHelpDesku += "Szukany dokument o numerze systemowym " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje si� w bazie centralnej, natomiast brak go w bazach lokalnych."			
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
					
						raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje si� w tabeli dokumenty w centrali, ale nie ma go w bazach lokalnych.\n"
							+ ((dok.getSymbolDokumentu() != null) 
								? "Dokument ma nadany sym_dok " + dok.getSymbolDokumentu() + ".\n"
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
					} else {
						raportDlaHelpDesku += "Szukany dokument o numerze ewidencyjnym " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje si� w bazie centralnej, natomiast brak go w bazach lokalnych."			
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? " Ma nadany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
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
					
						raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje si� w tabeli dokumenty w centrali, ale nie ma go w bazach lokalnych.\n"
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
					
					raporty.add(new Raport(raportDlaHelpDesku, raportDlaAdministratora));	
				}
			} else if (dokument.getDokumentyZIzbyDokumenty() != null) { 
				for (DokumentZIzbyDokumenty dok : dokument.getDokumentyZIzbyDokumenty()) {
					if (dokument.getSzukanyNumer().containsKey(Identyfikator.NUMER_AKT) == true) {
		
						raportDlaHelpDesku += "Szukany dokument o numerze w�asnym " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znajduje si� w bazie lokalnej."			
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? " Ma nadany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
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
				
						raportDlaAdministratora += "Dla szukanego nr_akt " + dokument.getSzukanyNumer().get(Identyfikator.NUMER_AKT) + " znaleziono odpowiadaj�cy dokument w tabeli dokumenty bazy lokalnej.\n"
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
					} else if (dokument.getSzukanyNumer().containsKey(Identyfikator.IDENTYFIKATOR_DOKUMENTU) == true) {	
						raportDlaHelpDesku += "Szukany dokument o numerze systemowym " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje si� w bazie lokalnej."			
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
					
						raportDlaAdministratora += "Szukany id_dok " + dokument.getSzukanyNumer().get(Identyfikator.IDENTYFIKATOR_DOKUMENTU) + " znajduje si� w tabeli dokumenty bazy lokalnej.\n"
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
					} else {
						raportDlaHelpDesku += "Szukany dokument o numerze ewidencyjnym " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje si� w bazie lokalnej."	
							+ ((dok.getIdentyfikatorDokumentu() != null) 
								? " Ma nadany numer systemowy " + dok.getIdentyfikatorDokumentu() + "."
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
					
						raportDlaAdministratora += "Szukany sym_dok " + dokument.getSzukanyNumer().get(Identyfikator.SYMBOL_DOKUMENTU) + " znajduje si� w tabeli dokumenty bazy lokalnej.\n"
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
					
					raporty.add(new Raport(raportDlaHelpDesku, raportDlaAdministratora));	
				}		
			}
		}
		
		return raporty;	
	}
}
