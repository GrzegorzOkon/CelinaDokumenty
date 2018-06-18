package kontroler;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import kontroler.wejscie.Dokument;
import kontroler.wejscie.DokumentZIzby;
import kontroler.wejscie.Identyfikator;
import kontroler.wejscie.WyszukajWCentraliPoIdentyfikatorachDokumentówZadanie;
import kontroler.wejscie.WyszukajWCentraliPoNumerachAktZadanie;
import procesor.Model;
import procesor.dao.sybase.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.sybase.entity.DokumentZCentralaDokumenty;
import procesor.dao.sybase.entity.DokumentZIzbyDokumenty;
import procesor.raporty.wejscie.Raport;
import procesor.wersja.wejscie.AktualnaWersja;
import widok.Widok;
import static javafx.concurrent.Worker.State.READY;

/**
 * Klasa kontrolera w architekturze mvc.
 * 
 * @author Grzegorz Okoń
 */
public class Kontroler {
	private Widok widok;
	private Model model;
	
    /**
     * Konstruktor klasy przypisujący referencje do obiektów.
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
	
	public String pobierzOpis() {
		return model.pobierzOpis();
	}
	
	public void porównajWersje() {
		Thread wątek = new Thread(new Runnable() {
		
			@Override
			public void run() {				
				model.porównajWersje();
			}
		});

		wątek.start();
	}
	
	/**
	 * Metoda wyszukująca dokumenty w bazie centralnej po podanych numerach własnych dokumentów i tworząca z otrzymanych danych raporty
	 * 
	 * @param numeryAkt - lista numerów własnych dokumentów po których nastąpi wyszukiwanie w bazie
	 */
	public void wyszukajWCentraliNrAkt(TreeSet<String> numeryAkt) {
		uruchomZadanie(new WyszukajWCentraliPoNumerachAktZadanie(widok, model, numeryAkt));
	}
	
	/**
	 * Metoda wyszukująca dokumenty w bazie centralnej po podanych identyfikatorach dokumentów i tworząca z otrzymanych danych raporty
	 * 
	 * @param identyfikatoryDokumentów - lista identyfikatorów dokumentów po których nastąpi wyszukiwanie w bazie
	 */
	public void wyszukajWCentraliIdDok(TreeSet<String> identyfikatoryDokumentów) {
		uruchomZadanie(new WyszukajWCentraliPoIdentyfikatorachDokumentówZadanie(widok, model, identyfikatoryDokumentów));
	}
	
    /**
     * Metoda wyszukująca dokumenty w bazie centralnej po podanych symbolach dokumentów i tworząca z otrzymanych danych raporty
     * 
     * @param symDok - lista numerów symboli dokumentów po których nastąpi wyszukiwanie w bazie
     */ 
	public void wyszukajWCentraliSymDok(TreeSet<String> symboleDokumentów) {
		uruchomZadanie(new WyszukajWCentraliPoIdentyfikatorachDokumentówZadanie(widok, model, symboleDokumentów));
	}
	
	/**
	 * Metoda uruchamia długotrwałe zadania w tle
	 * 
	 * @param zadanie - obiekt typu Task do wykonania
	 */
	private void uruchomZadanie(Task<ObservableList<Long>> zadanie) {		
		Thread wątekWTle = new Thread(zadanie);
		wątekWTle.setDaemon(true);
		wątekWTle.start();
	}
	
    /**
     * Wyszukuje dokumenty w bazie centralnej po podanych numerach własnych 
     * i następnie szuka w bazach lokalnych po odpowiadających im identyfikatorach dokumentów
     * 
     * @param numeryAkt jest listą numerów własnych (nr_akt w bazie) po których ma nastąpić wyszukiwanie
     */ 
	public void wyszukajLokalniePoNumerzeAkt(TreeSet<String> numeryAkt) {
		
		Thread watek = new Thread(new Runnable() {
			@Override
			public void run() {
				
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
						
						//Wpis do raportu o braku kodu oddziału w opisach
					} catch(Exception ex) {
						

					}
				}

				// Wyświetla odpowiedni raport oraz zapisuje dane "na boku" do ewentualnej analizy
				raporty = model.generujLokalneRaporty(dokumenty);
				widok.wyświetlRaporty(raporty);
				model.zapiszDoAnalizy(raporty);
			}
		});
		
		watek.start();
	}
	
    /**
     * Wyszukuje dokumenty w bazie centralnej po podanych identyfikatorach dokumentów (id_dok)
     * i następnie szuka w bazach lokalnych po tych samych numerach
     * 
     * @param identyfikatoryDokumentów jest listą identyfikatorów dokumentów (id_dok w bazie) po których ma nastąpić wyszukiwanie
     */ 
	public void wyszukajLokalniePoIdentyfikatorzeDokumentu(TreeSet<String> identyfikatoryDokumentów) {
		Thread watek = new Thread(new Runnable() {

			@Override
			public void run() {
				ArrayList<DokumentZIzby> dokumenty = new ArrayList<>();
				List<Raport> raporty = new ArrayList<>();
				
				for (String identyfikatorDokumentu : identyfikatoryDokumentów) {			
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
						//Wpis do raportu o braku kodu oddziału w opisach
					} catch (Exception ex) {

					}	
				}
				
				// Wyświetla odpowiedni raport oraz zapisuje dane "na boku" do ewentualnej analizy
				raporty = model.generujLokalneRaporty(dokumenty);
				widok.wyświetlRaporty(raporty);
				model.zapiszDoAnalizy(raporty);
			}
		});
		
		watek.start();
	}
	
    /**
     * Wyszukuje dokumenty w bazie centralnej po podanych symbolach dokumentów (sym_dok)
     * i następnie szuka w bazach lokalnych po odpowiadających im identyfikatorach dokumentów
     * 
     * @param symboleDokumentów jest listą symboli dokumentów (sym_dok w bazie) po których ma nastąpić wyszukiwanie
     */ 
	public void wyszukajLokalniePoSymboluDokumentu(TreeSet<String> symboleDokumentów) {
		
		Thread watek = new Thread(new Runnable() {
			@Override
			public void run() {
				
				ArrayList<DokumentZIzby> dokumenty = new ArrayList<>();
				List<Raport> raporty = new ArrayList<>();
				
				for (String symbolDokumentu : symboleDokumentów) {
					
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
						
						//Wpis do raportu o braku kodu oddziału w opisach
					} catch(Exception ex) {

					}
				}

				// Wyświetla odpowiedni raport oraz zapisuje dane "na boku" do ewentualnej analizy
				raporty = model.generujLokalneRaporty(dokumenty);
				widok.wyświetlRaporty(raporty);
				model.zapiszDoAnalizy(raporty);
			}
		});
		
		watek.start();		
	}
}
