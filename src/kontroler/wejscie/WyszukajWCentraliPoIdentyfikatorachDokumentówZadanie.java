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

public class WyszukajWCentraliPoIdentyfikatorachDokument�wZadanie extends Task<ObservableList<Long>> {
	private Widok widok = null;
	private Model model = null;
	private TreeSet<String> identyfikatoryDokument�w = null;
	
	public WyszukajWCentraliPoIdentyfikatorachDokument�wZadanie(Widok widok, Model model, TreeSet<String> identyfikatoryDokument�w) {
		this.widok = widok;
		this.model = model;
		this.identyfikatoryDokument�w = identyfikatoryDokument�w;
	}

	@Override
	protected ObservableList<Long> call() {
		//Obserwowalna lista do reprezentowania rezultat�w
		final ObservableList<Long> rezultaty = FXCollections.<Long>observableArrayList();	
		
		ArrayList<Dokument> dokumenty = new ArrayList<>();
		List<Raport> raporty = new ArrayList<>();
		
		for (String identyfikatorDokumentu : identyfikatoryDokument�w) {
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

				break;  //wy�wietla raz komunikat b��du dla listy komunikat�w
			}		
		}
		
		// Wy�wietla odpowiedni raport oraz zapisuje dane "na boku" do ewentualnej analizy
		raporty = model.generujCentralneRaporty(dokumenty);
		widok.wy�wietlRaporty(raporty);
		model.zapiszDoAnalizy(raporty);
		
		return rezultaty;
	}
}
