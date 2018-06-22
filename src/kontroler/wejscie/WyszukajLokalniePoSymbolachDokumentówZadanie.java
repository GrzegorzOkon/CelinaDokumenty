package kontroler.wejscie;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import procesor.Model;
import procesor.dao.sybase.entity.DokumentZCentralaDokumenty;
import procesor.dao.sybase.entity.DokumentZIzbyDokumenty;
import procesor.raporty.wejscie.Raport;
import widok.Widok;

public class WyszukajLokalniePoSymbolachDokument�wZadanie extends Task<ObservableList<Long>> {
	private Widok widok = null;
	private Model model = null;
	private TreeSet<String> symboleDokument�w = null;
	
	public WyszukajLokalniePoSymbolachDokument�wZadanie(Widok widok, Model model, TreeSet<String> symboleDokument�w) {
		this.widok = widok;
		this.model = model;
		this.symboleDokument�w = symboleDokument�w;
	}

	@Override
	protected ObservableList<Long> call() {
		//Obserwowalna lista do reprezentowania rezultat�w
		final ObservableList<Long> rezultaty = FXCollections.<Long>observableArrayList();
	
		ArrayList<DokumentZIzby> dokumenty = new ArrayList<>();
		List<Raport> raporty = new ArrayList<>();
	
		for (String symbolDokumentu : symboleDokument�w) {
			try {
				List<DokumentZCentralaDokumenty> dokumentyZTabeliDokumenty = model.findBySymDokInDokumenty(symbolDokumentu);
			
				if (dokumentyZTabeliDokumenty != null) {
					for (DokumentZCentralaDokumenty dokumentZTabeliDokumenty : dokumentyZTabeliDokumenty) {
						DokumentZIzby dokumentIzbowy = new DokumentZIzby(Identyfikator.SYMBOL_DOKUMENTU, symbolDokumentu);	
						dokumentIzbowy.setDokumentyZCentralaDokumenty(dokumentZTabeliDokumenty);
						dokumenty.add(dokumentIzbowy);	
													
						DokumentZIzbyDokumenty dokumentLokalnyZTabeliDokumenty = model.findByIdDokInDokumenty(dokumentZTabeliDokumenty.getIdentyfikatorDokumentu(), dokumentZTabeliDokumenty.getIdentyfikatorJednostki());
						dokumentIzbowy.setDokumentyZIzbyDokumenty(dokumentLokalnyZTabeliDokumenty);
					}
				} else {
					DokumentZIzby dokumentIzbowy = new DokumentZIzby(Identyfikator.SYMBOL_DOKUMENTU, symbolDokumentu);	
					dokumenty.add(dokumentIzbowy);	
				}
			} catch(NullPointerException ex) {
				//Wpis do raportu o braku kodu oddzia�u w opisach
			} catch(Exception ex) {}
		}

		// Wy�wietla odpowiedni raport oraz zapisuje dane "na boku" do ewentualnej analizy
		raporty = model.generujLokalneRaporty(dokumenty);
		widok.wy�wietlRaporty(raporty);
		model.zapiszDoAnalizy(raporty);
		
		return rezultaty;
	}
}
