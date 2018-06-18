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

public class WyszukajWCentraliPoSymbolachDokument�w extends Task<ObservableList<Long>> {
	private Widok widok = null;
	private Model model = null;
	private TreeSet<String> symboleDokument�w = null;
	
	public WyszukajWCentraliPoSymbolachDokument�w(Widok widok, Model model, TreeSet<String> symboleDokument�w) {
		this.widok = widok;
		this.model = model;
		this.symboleDokument�w = symboleDokument�w;
	}

	@Override
	protected ObservableList<Long> call() {
		//Obserwowalna lista do reprezentowania rezultat�w
		final ObservableList<Long> rezultaty = FXCollections.<Long>observableArrayList();
	
		ArrayList<Dokument> dokumenty = new ArrayList<>();
		List<Raport> raporty = new ArrayList<>();
		
		for (String symbolDokumentu : symboleDokument�w) {
			Dokument dokument = new Dokument(Identyfikator.SYMBOL_DOKUMENTU, symbolDokumentu);
			dokumenty.add(dokument);
			
			try {
				List<DokumentZCentralaDokumenty> dokumentyZTabeliDokumenty = model.findBySymDokInDokumenty(symbolDokumentu);
				
				dokument.setDokumentyZCentralaDokumenty(dokumentyZTabeliDokumenty);
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
