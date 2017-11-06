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
 * @author Grzegorz Oko�
 */
public class Kontroler {
	private Widok widok;
	private Model model;
	
    /**
     * Konstruktor klasy przypisuj�cy referencje do obiekt�w.
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
				//widok.wy�wietlRaport("wykonano select po numerze akt\n");
				
				if (dokumentyZTabeliCntrValidDok != null) {				
					for (DokumentZCentralaCntrValidDok dokumentZTabeliCntrValidDok : dokumentyZTabeliCntrValidDok) {
						if (dokumentZTabeliCntrValidDok.getIdentyfikatorDokumentu() != null) {						
							DokumentZCentralaDokumenty dokumentZTabeliDokumenty = model.findByIdDokInDokumenty(dokumentZTabeliCntrValidDok.getIdentyfikatorDokumentu());
							dokument.setDokumentZCentralaDokumenty(dokumentZTabeliDokumenty);	
							
							//TEST
							//widok.wy�wietlRaport(dokumentZTabeliDokumenty.getIdentyfikatorDokumentu() + "\n");
							//widok.wy�wietlRaport(dokumentZTabeliDokumenty.getSymbolDokumentu() + "\n");
						}
					}				
				}
			} catch (Exception ex) {
				widok.wy�wietlKomunikatB�edu();
				break;  //wy�wietla raz komunikat b��du dla listy komunikat�w
			}	
		}
		
		// Wy�wietla odpowiedni raport
		model.generujRaporty(dokumenty);
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
				//widok.wy�wietlRaport("" + dokumentZTabeliDokumenty.getIdentyfikatorDokumentu());
				
				dokument.setDokumentZCentralaDokumenty(dokumentZTabeliDokumenty);
				
				if (dokumentZTabeliDokumenty == null) {
					//TEST
					//widok.wy�wietlRaport("brak w dokumentach\n");
					
					DokumentZCentralaCntrValidDok dokumentZTabeliCntrValidDok = model.findByIdDokInCntrValidDok(identyfikatorDokumentu);
					dokument.setDokumentyZCentralaCntrValidDok(dokumentZTabeliCntrValidDok);
					
					//TEST
					/*if (dokumentZTabeliCntrValidDok == null) {
						widok.wy�wietlRaport("brak w cntr_valid_dok\n");
					} else {
						widok.wy�wietlRaport(dokumentZTabeliCntrValidDok.getIdentyfikatorDokumentu());
					}*/
				} 
				//TEST
				//else {
					//TEST
					//widok.wy�wietlRaport(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() + "\n");
				//}
				
			} catch (Exception ex) {
				widok.wy�wietlKomunikatB�edu();
				break;  //wy�wietla raz komunikat b��du dla listy komunikat�w
			}		
		}
		
		// Wy�wietla odpowiedni raport
		model.generujRaporty(dokumenty);
	}
	
    /**
     * Metoda wyszukuj�ca dokumenty w bazie centralnej po podanych symbolach dokument�w i tworz�ca z otrzymanych danych raporty.
     * 
     * @param symDok
     * 			 Lista numer�w symboli dokument�w po kt�rych nastapi wyszukiwanie w bazie.
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
				/*if (dokumentZTabeliDokumenty == null) {
					//TEST
					widok.wy�wietlRaport("brak w dokumentach\n");
				} else {
					//TEST
					widok.wy�wietlRaport(dokumentZTabeliDokumenty.getSymbolDokumentu());
				}*/
			} catch (Exception ex) {
				widok.wy�wietlKomunikatB�edu();
				break;  //wy�wietla raz komunikat b��du dla listy komunikat�w
			}
		}
		
		// Wy�wietla odpowiedni raport
		model.generujRaporty(dokumenty);
	}
}