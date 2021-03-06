package kontroler;

import java.util.TreeSet;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import kontroler.wejscie.*;
import procesor.Model;
import widok.Widok;

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
	public void wyszukajWCentraliPoNumerachAkt(TreeSet<String> numeryAkt) {
		uruchomZadanie(new WyszukajWCentraliPoNumerachAktZadanie(widok, model, numeryAkt));
	}
	
	/**
	 * Metoda wyszukująca dokumenty w bazie centralnej po podanych identyfikatorach dokumentów i tworząca z otrzymanych danych raporty
	 * 
	 * @param identyfikatoryDokumentów - lista identyfikatorów dokumentów po których nastąpi wyszukiwanie w bazie
	 */
	public void wyszukajWCentraliPoIdentyfikatorachDokumentów(TreeSet<String> identyfikatoryDokumentów) {
		uruchomZadanie(new WyszukajWCentraliPoIdentyfikatorachDokumentówZadanie(widok, model, identyfikatoryDokumentów));
	}
	
    /**
     * Metoda wyszukująca dokumenty w bazie centralnej po podanych symbolach dokumentów i tworząca z otrzymanych danych raporty
     * 
     * @param symDok - lista numerów symboli dokumentów po których nastąpi wyszukiwanie w bazie
     */ 
	public void wyszukajWCentraliPoSymbolachDokumentów(TreeSet<String> symboleDokumentów) {
		uruchomZadanie(new WyszukajWCentraliPoSymbolachDokumentówZadanie(widok, model, symboleDokumentów));
	}
	
	/**
	 * Metoda wyszukująca dokumenty w bazie centralnej po podanych numerach własnych dokumentów, a następnie szukająca na bazie lokalnej i tworząca z otrzymanych danych raporty
	 * 
	 * @param numeryAkt - lista numerów własnych dokumentów po których nastąpi wyszukiwanie w bazie
	 */
	public void wyszukajLokalniePoNumerachAkt(TreeSet<String> numeryAkt) {
		uruchomZadanie(new WyszukajLokalniePoNumerachAktZadanie(widok, model, numeryAkt));
	}
	
	/**
	 * Metoda wyszukująca dokumenty w bazie centralnej po podanych identyfikatorach dokumentów, a następnie szukająca na bazie lokalnej i tworząca z otrzymanych danych raporty
	 * 
	 * @param numeryAkt - lista identyfikatorów dokumentów po których nastąpi wyszukiwanie w bazie
	 */
	public void wyszukajLokalniePoIdentyfikatorachDokumentów(TreeSet<String> identyfikatoryDokumentów) {
		uruchomZadanie(new WyszukajLokalniePoIdentyfikatorachDokumentówZadanie(widok, model, identyfikatoryDokumentów));
	}
	
	/**
	 * Metoda wyszukująca dokumenty w bazie centralnej po podanych symbolach dokumentów, a następnie szukająca na bazie lokalnej i tworząca z otrzymanych danych raporty
	 * 
	 * @param numeryAkt - lista symboli dokumentów po których nastąpi wyszukiwanie w bazie
	 */ 
	public void wyszukajLokalniePoSymbolachDokumentów(TreeSet<String> symboleDokumentów) {
		uruchomZadanie(new WyszukajLokalniePoSymbolachDokumentówZadanie(widok, model, symboleDokumentów));	
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
}