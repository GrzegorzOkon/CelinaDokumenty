package kontroler;

import java.util.ArrayList;
import java.util.List;

import kontroler.wejscie.Dokument;
import kontroler.wejscie.Identyfikator;
import procesor.Model;
import procesor.dao.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.entity.DokumentZCentralaDokumenty;
import widok.Widok;

/**
 * Klasa kontrolera w architekturze mvc.
 * 
 * @author Grzegorz Okoñ
 */
public class Kontroler {
	private Widok widok;
	private Model model;
	
    /**
     * Konstruktor klasy przypisuj¹cy referencje do obiektów.
     * 
     * @param widok
     *           Referencja do widoku.
     * @param model 
     *           Referencja do modelu.
     */  
	public Kontroler(Widok widok, Model model) {
		this.widok = widok;
		this.model = model;
	}
	
	public void wyszukajWCentraliNrAkt(ArrayList<String> numeryAkt) {
		ArrayList<Dokument> dokumenty = new ArrayList<>();
		
		for (String numerAkt : numeryAkt) {
			Dokument dokument = new Dokument(Identyfikator.NUMER_AKT, numerAkt);
			dokumenty.add(dokument);
			
			try {
				List<DokumentZCentralaCntrValidDok> dokumentyZTabeliCntrValidDok = model.findByNrAktInCntrValidDok(numerAkt);
				dokument.setDokumentyZCentralaCntrValidDok(dokumentyZTabeliCntrValidDok);
			
				//TEST
				//widok.wyœwietlRaport("wykonano select\n");
				
				if (dokumentyZTabeliCntrValidDok != null && dokumentyZTabeliCntrValidDok.size() > 0) {				
					for (DokumentZCentralaCntrValidDok dokumentZTabeliCntrValidDok : dokumentyZTabeliCntrValidDok) {
						if (dokumentZTabeliCntrValidDok != null && dokumentZTabeliCntrValidDok.getIdentyfikatorDokumentu() != null) {						
							DokumentZCentralaDokumenty dokumentZTabeliDokumenty = model.findByIdDokInDokumenty(dokumentZTabeliCntrValidDok.getIdentyfikatorDokumentu());
							dokument.setDokumentZCentralaDokumenty(dokumentZTabeliDokumenty);					
						}
					}				
				}
			} catch (Exception ex) {
				widok.wyœwietlKomunikatB³edu();
				break;  //wyœwietla raz komunikat b³êdu dla listy komunikatów
			}	
		}
		
		// Wyœwietla odpowiedni raport
		//model.generujRaporty(dokumenty);
	}
	
	//
	public void wyszukajWCentraliIdDok(ArrayList<String> idDoki) {
		ArrayList<Dokument> dokumenty = new ArrayList<>();
		
		for (String identyfikatorDokumentu : idDoki) {
			Dokument dokument = new Dokument(Identyfikator.IDENTYFIKATOR_DOKUMENTU, identyfikatorDokumentu);
			dokumenty.add(dokument);
			
			try {
				DokumentZCentralaDokumenty dokumentZTabeliDokumenty = model.findByIdDokInDokumenty(identyfikatorDokumentu);					
				
				//TEST
				//widok.wyœwietlRaport("" + dokumentZTabeliDokumenty.getIdentyfikatorDokumentu());
				
				dokument.setDokumentZCentralaDokumenty(dokumentZTabeliDokumenty);
				
				if (dokumentZTabeliDokumenty == null) {
					//TEST
					widok.wyœwietlRaport("brak w dokumentach\n");
					
					DokumentZCentralaCntrValidDok dokumentZTabeliCntrValidDok = model.findByIdDokInCntrValidDok(identyfikatorDokumentu);
					dokument.setDokumentyZCentralaCntrValidDok(dokumentZTabeliCntrValidDok);
					
					//TEST
					if (dokumentZTabeliCntrValidDok == null) {
						widok.wyœwietlRaport("brak w cntr_valid_dok\n");
					} else {
						widok.wyœwietlRaport(dokumentZTabeliCntrValidDok.getIdentyfikatorDokumentu());
					}
				} 
				//TEST
				else {
					//TEST
					widok.wyœwietlRaport(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() + "\n");
				}
				
			} catch (Exception ex) {
				widok.wyœwietlKomunikatB³edu();
				break;  //wyœwietla raz komunikat b³êdu dla listy komunikatów
			}		
		}
	}
	
    /**
     * Metoda wyszukuj¹ca dokumenty w bazie centralnej po podanych symbolach dokumentów i tworz¹ca z otrzymanych danych raporty.
     * 
     * @param symDok
     * 			 Lista numerów symboli dokumentów po których nastapi wyszukiwanie w bazie.
     */ 
	public void wyszukajWCentraliSymDok(ArrayList<String> symDok) {
		ArrayList<Dokument> dokumenty = new ArrayList<>();
		
		for (String symbolDokumentu : symDok) {
			Dokument dokument = new Dokument(Identyfikator.SYMBOL_DOKUMENTU, symbolDokumentu);
			dokumenty.add(dokument);
			
			try {
				DokumentZCentralaDokumenty dokumentZTabeliDokumenty = model.findBySymDokInDokumenty(symbolDokumentu);
				
				dokument.setDokumentZCentralaDokumenty(dokumentZTabeliDokumenty);
			
				//TEST
				if (dokumentZTabeliDokumenty == null) {
					//TEST
					widok.wyœwietlRaport("brak w dokumentach\n");
				} else {
					//TEST
					widok.wyœwietlRaport(dokumentZTabeliDokumenty.getSymbolDokumentu());
				}
			} catch (Exception ex) {
				widok.wyœwietlKomunikatB³edu();
				break;  //wyœwietla raz komunikat b³êdu dla listy komunikatów
			}
		}
	}
}
