package kontroler;

import java.util.ArrayList;

import kontroler.wejscie.Dokument;
import kontroler.wejscie.Identyfikator;
import procesor.Model;
import procesor.dao.entity.DokumentZCentralaCntrValidDok;
//import procesor.dao.DokumentZCentralaCntrValidDok;
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
}
