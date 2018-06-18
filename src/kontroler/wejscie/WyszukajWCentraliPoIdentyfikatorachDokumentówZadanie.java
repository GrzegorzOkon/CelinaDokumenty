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

public class WyszukajWCentraliPoIdentyfikatorachDokumentówZadanie extends Task<ObservableList<Long>> {
	private Widok widok = null;
	private Model model = null;
	private TreeSet<String> identyfikatoryDokumentów = null;
	
	public WyszukajWCentraliPoIdentyfikatorachDokumentówZadanie(Widok widok, Model model, TreeSet<String> identyfikatoryDokumentów) {
		this.widok = widok;
		this.model = model;
		this.identyfikatoryDokumentów = identyfikatoryDokumentów;
	}

	@Override
	protected ObservableList<Long> call() {
		//Obserwowalna lista do reprezentowania rezultatów
		final ObservableList<Long> rezultaty = FXCollections.<Long>observableArrayList();	
		
		ArrayList<Dokument> dokumenty = new ArrayList<>();
		List<Raport> raporty = new ArrayList<>();
		
		for (String identyfikatorDokumentu : identyfikatoryDokumentów) {
			Dokument dokument = new Dokument(Identyfikator.IDENTYFIKATOR_DOKUMENTU, identyfikatorDokumentu);
			dokumenty.add(dokument);

			try {
				DokumentZCentralaDokumenty dokumentZTabeliDokumenty = model.findByIdDokInDokumenty(identyfikatorDokumentu);										
				dokument.setDokumentyZCentralaDokumenty(dokumentZTabeliDokumenty);
		
				if (dokumentZTabeliDokumenty == null) {					
					DokumentZCentralaCntrValidDok dokumentZTabeliCntrValidDok = model.findByIdDokInCntrValidDok(identyfikatorDokumentu);
					dokument.setDokumentyZCentralaCntrValidDok(dokumentZTabeliCntrValidDok);
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
