package kontroler.wejscie;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import procesor.Model;
import procesor.dao.sybase.entity.DokumentZCentralaDokumenty;
import procesor.raporty.wejscie.Raport;
import widok.Widok;

public class WyszukajWCentraliPoSymbolachDokumentów extends Task<ObservableList<Long>> {
	private Widok widok = null;
	private Model model = null;
	private TreeSet<String> symboleDokumentów = null;
	
	public WyszukajWCentraliPoSymbolachDokumentów(Widok widok, Model model, TreeSet<String> symboleDokumentów) {
		this.widok = widok;
		this.model = model;
		this.symboleDokumentów = symboleDokumentów;
	}

	@Override
	protected ObservableList<Long> call() {
		//Obserwowalna lista do reprezentowania rezultatów
		final ObservableList<Long> rezultaty = FXCollections.<Long>observableArrayList();
	
		ArrayList<Dokument> dokumenty = new ArrayList<>();
		List<Raport> raporty = new ArrayList<>();
		
		for (String symbolDokumentu : symboleDokumentów) {
			Dokument dokument = new Dokument(Identyfikator.SYMBOL_DOKUMENTU, symbolDokumentu);
			dokumenty.add(dokument);
			
			try {
				List<DokumentZCentralaDokumenty> dokumentyZTabeliDokumenty = model.findBySymDokInDokumenty(symbolDokumentu);
				
				dokument.setDokumentyZCentralaDokumenty(dokumentyZTabeliDokumenty);
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
