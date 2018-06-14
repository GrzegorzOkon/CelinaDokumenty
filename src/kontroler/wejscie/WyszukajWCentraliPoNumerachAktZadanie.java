package kontroler.wejscie;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import procesor.Model;
import procesor.dao.sybase.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.sybase.entity.DokumentZCentralaDokumenty;
import procesor.raporty.wejscie.Raport;
import widok.Widok;

public class WyszukajWCentraliPoNumerachAktZadanie extends Task<ObservableList<Long>> {
	private Widok widok = null;
	private Model model = null;
	private TreeSet<String> numeryAkt = null;
	
	public WyszukajWCentraliPoNumerachAktZadanie(Widok widok, Model model, TreeSet<String> numeryAkt) {
		this.widok = widok;
		this.model = model;
		this.numeryAkt = numeryAkt;
	}
	
	@Override
	protected ObservableList<Long> call() {
		//Obserwowalna lista do reprezentowania rezultatów
		final ObservableList<Long> rezultaty = FXCollections.<Long>observableArrayList();
		
		//Update tytu³u
		this.updateTitle("Zadanie wyszukania w centrali po numerach akt");
		
		ArrayList<Dokument> dokumenty = new ArrayList<>();
		List<Raport> raporty = new ArrayList<>();
		
		for (String numerAkt : numeryAkt) {			
			try {
				List<DokumentZCentralaCntrValidDok> dokumentyZTabeliCntrValidDok = model.findByNrAktInCntrValidDok(numerAkt);
				
				if (dokumentyZTabeliCntrValidDok != null) {
					for (DokumentZCentralaCntrValidDok dokumentZTabeliCntrValidDok : dokumentyZTabeliCntrValidDok) {
						Dokument dokument = new Dokument(Identyfikator.NUMER_AKT, numerAkt);
						dokumenty.add(dokument);
					
						dokument.setDokumentyZCentralaCntrValidDok(dokumentZTabeliCntrValidDok);

						if (dokumentZTabeliCntrValidDok.getIdentyfikatorDokumentu() != null) {						
							DokumentZCentralaDokumenty dokumentZTabeliDokumenty = model.findByIdDokInDokumenty(dokumentZTabeliCntrValidDok.getIdentyfikatorDokumentu());
							dokument.setDokumentyZCentralaDokumenty(dokumentZTabeliDokumenty);	
						}				
					}
				} else {
					Dokument dokument = new Dokument(Identyfikator.NUMER_AKT, numerAkt);
					dokumenty.add(dokument);
				}
			} catch (Exception ex) {

				break;  //wyœwietla raz komunikat b³êdu dla listy komunikatów
			}	
		}

		// Wyœwietla odpowiedni raport oraz zapisuje dane "na boku" do ewentualnej analizy
		raporty = model.generujCentralneRaporty(dokumenty);
		widok.wyœwietlRaporty(raporty);
		model.zapiszDoAnalizy(raporty);
		
		return rezultaty;
	}
}
