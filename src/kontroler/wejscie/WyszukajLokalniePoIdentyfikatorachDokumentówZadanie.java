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

public class WyszukajLokalniePoIdentyfikatorachDokument�wZadanie extends Task<ObservableList<Long>> {
	private Widok widok = null;
	private Model model = null;
	private TreeSet<String> identyfikatoryDokument�w = null;
	
	public WyszukajLokalniePoIdentyfikatorachDokument�wZadanie(Widok widok, Model model, TreeSet<String> identyfikatoryDokument�w) {
		this.widok = widok;
		this.model = model;
		this.identyfikatoryDokument�w = identyfikatoryDokument�w;
	}

	@Override
	protected ObservableList<Long> call() {
		//Obserwowalna lista do reprezentowania rezultat�w
		final ObservableList<Long> rezultaty = FXCollections.<Long>observableArrayList();	
		
		ArrayList<DokumentZIzby> dokumenty = new ArrayList<>();
		List<Raport> raporty = new ArrayList<>();
		
		for (String identyfikatorDokumentu : identyfikatoryDokument�w) {			
			try {
				DokumentZIzby dokumentIzbowy = new DokumentZIzby(Identyfikator.IDENTYFIKATOR_DOKUMENTU, identyfikatorDokumentu);	
				dokumenty.add(dokumentIzbowy);	
				
				DokumentZCentralaDokumenty dokumentZTabeliDokumenty = model.findByIdDokInDokumenty(identyfikatorDokumentu);
				dokumentIzbowy.setDokumentyZCentralaDokumenty(dokumentZTabeliDokumenty);
				
				if (dokumentZTabeliDokumenty != null) {
					DokumentZIzbyDokumenty dokumentLokalnyZTabeliDokumenty = model.findByIdDokInDokumenty(identyfikatorDokumentu, dokumentZTabeliDokumenty.getIdentyfikatorJednostki());
					dokumentIzbowy.setDokumentyZIzbyDokumenty(dokumentLokalnyZTabeliDokumenty);
				} else {
					DokumentZCentralaCntrValidDok dokumentZTabeliCntrValidDok = model.findByIdDokInCntrValidDok(identyfikatorDokumentu);
					dokumentIzbowy.setDokumentyZCentralaCntrValidDok(dokumentZTabeliCntrValidDok);
					
					if (dokumentZTabeliCntrValidDok != null) {
						if (dokumentZTabeliCntrValidDok.getJednostkaPrzeznaczenia() != null) {
							DokumentZIzbyDokumenty dokumentLokalnyZTabeliDokumenty = model.findByIdDokInDokumenty(identyfikatorDokumentu, dokumentZTabeliCntrValidDok.getJednostkaPrzeznaczenia());
							dokumentIzbowy.setDokumentyZIzbyDokumenty(dokumentLokalnyZTabeliDokumenty);
						}
					} 
				}
			} catch (NullPointerException ex) {
				//Wpis do raportu o braku kodu oddzia�u w opisach
			} catch (Exception ex) {

			}	
		}
		
		// Wy�wietla odpowiedni raport oraz zapisuje dane "na boku" do ewentualnej analizy
		raporty = model.generujLokalneRaporty(dokumenty);
		widok.wy�wietlRaporty(raporty);
		model.zapiszDoAnalizy(raporty);
		
		return rezultaty;
	}
}
