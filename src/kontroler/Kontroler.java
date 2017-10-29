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
					widok.wy�wietlRaport("brak w dokumentach\n");
					
					DokumentZCentralaCntrValidDok dokumentZTabeliCntrValidDok = model.findByIdDokInCntrValidDok(identyfikatorDokumentu);
					dokument.setDokumentyZCentralaCntrValidDok(dokumentZTabeliCntrValidDok);
					
					//TEST
					if (dokumentZTabeliCntrValidDok == null) {
						widok.wy�wietlRaport("brak w cntr_valid_dok\n");
					} else {
						widok.wy�wietlRaport(dokumentZTabeliCntrValidDok.getIdentyfikatorDokumentu());
					}
				} 
				//TEST
				else {
					//TEST
					widok.wy�wietlRaport(dokument.getDokumentZCentralaDokumenty().getIdentyfikatorDokumentu() + "\n");
				}
				
			} catch (Exception ex) {
				widok.wy�wietlKomunikatB�edu();
				break;  //wy�wietla raz komunikat b��du dla listy komunikat�w
			}		
		}
	}
}
