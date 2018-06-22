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
import procesor.dao.sybase.entity.DokumentZIzbyDokumenty;
import procesor.raporty.wejscie.Raport;
import widok.Widok;

public class WyszukajLokalniePoNumerachAktZadanie extends Task<ObservableList<Long>> {
	private Widok widok = null;
	private Model model = null;
	private TreeSet<String> numeryAkt = null;

	public WyszukajLokalniePoNumerachAktZadanie(Widok widok, Model model, TreeSet<String> numeryAkt) {
		this.widok = widok;
		this.model = model;
		this.numeryAkt = numeryAkt;
	}

	@Override
	protected ObservableList<Long> call() {
		//Obserwowalna lista do reprezentowania rezultatów
		final ObservableList<Long> rezultaty = FXCollections.<Long>observableArrayList();
		
		ArrayList<DokumentZIzby> dokumenty = new ArrayList<>();
		List<Raport> raporty = new ArrayList<>();
		
		for (String numerAkt : numeryAkt) {
			try {
				List<DokumentZCentralaCntrValidDok> dokumentyZTabeliCntrValidDok = model.findByNrAktInCntrValidDok(numerAkt);

				if (dokumentyZTabeliCntrValidDok != null) {
					for (DokumentZCentralaCntrValidDok dokumentZTabeliCntrValidDok : dokumentyZTabeliCntrValidDok) {
						DokumentZIzby dokumentIzbowy = new DokumentZIzby(Identyfikator.NUMER_AKT, numerAkt);	
						dokumentIzbowy.setDokumentyZCentralaCntrValidDok(dokumentZTabeliCntrValidDok);
						dokumenty.add(dokumentIzbowy);	
						
						DokumentZCentralaDokumenty dokumentZTabeliDokumenty = model.findByIdDokInDokumenty(dokumentZTabeliCntrValidDok.getIdentyfikatorDokumentu());
						dokumentIzbowy.setDokumentyZCentralaDokumenty(dokumentZTabeliDokumenty);

						DokumentZIzbyDokumenty dokumentLokalnyZTabeliDokumenty = model.findByIdDokInDokumenty(dokumentZTabeliCntrValidDok.getIdentyfikatorDokumentu(), dokumentZTabeliCntrValidDok.getJednostkaPrzeznaczenia());
						dokumentIzbowy.setDokumentyZIzbyDokumenty(dokumentLokalnyZTabeliDokumenty.getSymbolDokumentu());
					}
				} else {
					DokumentZIzby dokumentIzbowy = new DokumentZIzby(Identyfikator.NUMER_AKT, numerAkt);	
					dokumenty.add(dokumentIzbowy);	
				}
			} catch(NullPointerException ex) {
				//Wpis do raportu o braku kodu oddzia³u w opisach
			} catch(Exception ex) {}
		}
		
		// Wyœwietla odpowiedni raport oraz zapisuje dane "na boku" do ewentualnej analizy
		raporty = model.generujLokalneRaporty(dokumenty);
		widok.wyœwietlRaporty(raporty);
		model.zapiszDoAnalizy(raporty);
	
		return rezultaty;
	}
}
