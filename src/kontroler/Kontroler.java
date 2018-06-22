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
 * @author Grzegorz Okoñ
 */
public class Kontroler {
	private Widok widok;
	private Model model;
	
    /**
     * Konstruktor klasy przypisuj¹cy referencje do obiektów.
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
		Thread w¹tek = new Thread(new Runnable() {
		
			@Override
			public void run() {				
				model.porównajWersje();
			}
		});

		w¹tek.start();
	}
	
	/**
	 * Metoda wyszukuj¹ca dokumenty w bazie centralnej po podanych numerach w³asnych dokumentów i tworz¹ca z otrzymanych danych raporty
	 * 
	 * @param numeryAkt - lista numerów w³asnych dokumentów po których nast¹pi wyszukiwanie w bazie
	 */
	public void wyszukajWCentraliPoNumerachAkt(TreeSet<String> numeryAkt) {
		uruchomZadanie(new WyszukajWCentraliPoNumerachAktZadanie(widok, model, numeryAkt));
	}
	
	/**
	 * Metoda wyszukuj¹ca dokumenty w bazie centralnej po podanych identyfikatorach dokumentów i tworz¹ca z otrzymanych danych raporty
	 * 
	 * @param identyfikatoryDokumentów - lista identyfikatorów dokumentów po których nast¹pi wyszukiwanie w bazie
	 */
	public void wyszukajWCentraliPoIdentyfikatorachDokumentów(TreeSet<String> identyfikatoryDokumentów) {
		uruchomZadanie(new WyszukajWCentraliPoIdentyfikatorachDokumentówZadanie(widok, model, identyfikatoryDokumentów));
	}
	
    /**
     * Metoda wyszukuj¹ca dokumenty w bazie centralnej po podanych symbolach dokumentów i tworz¹ca z otrzymanych danych raporty
     * 
     * @param symDok - lista numerów symboli dokumentów po których nast¹pi wyszukiwanie w bazie
     */ 
	public void wyszukajWCentraliPoSymbolachDokumentów(TreeSet<String> symboleDokumentów) {
		uruchomZadanie(new WyszukajWCentraliPoSymbolachDokumentówZadanie(widok, model, symboleDokumentów));
	}
	
	/**
	 * Metoda wyszukuj¹ca dokumenty w bazie centralnej po podanych numerach w³asnych dokumentów, a nastêpnie szukaj¹ca na bazie lokalnej i tworz¹ca z otrzymanych danych raporty
	 * 
	 * @param numeryAkt - lista numerów w³asnych dokumentów po których nast¹pi wyszukiwanie w bazie
	 */
	public void wyszukajLokalniePoNumerachAkt(TreeSet<String> numeryAkt) {
		uruchomZadanie(new WyszukajLokalniePoNumerachAktZadanie(widok, model, numeryAkt));
	}
	
	/**
	 * Metoda wyszukuj¹ca dokumenty w bazie centralnej po podanych identyfikatorach dokumentów, a nastêpnie szukaj¹ca na bazie lokalnej i tworz¹ca z otrzymanych danych raporty
	 * 
	 * @param numeryAkt - lista identyfikatorów dokumentów po których nast¹pi wyszukiwanie w bazie
	 */
	public void wyszukajLokalniePoIdentyfikatorachDokumentów(TreeSet<String> identyfikatoryDokumentów) {
		uruchomZadanie(new WyszukajLokalniePoIdentyfikatorachDokumentówZadanie(widok, model, identyfikatoryDokumentów));
	}
	
	/**
	 * Metoda wyszukuj¹ca dokumenty w bazie centralnej po podanych symbolach dokumentów, a nastêpnie szukaj¹ca na bazie lokalnej i tworz¹ca z otrzymanych danych raporty
	 * 
	 * @param numeryAkt - lista symboli dokumentów po których nast¹pi wyszukiwanie w bazie
	 */ 
	public void wyszukajLokalniePoSymbolachDokumentów(TreeSet<String> symboleDokumentów) {
		uruchomZadanie(new WyszukajLokalniePoSymbolachDokumentówZadanie(widok, model, symboleDokumentów));	
	}
	
	/**
	 * Metoda uruchamia d³ugotrwa³e zadania w tle
	 * 
	 * @param zadanie - obiekt typu Task do wykonania
	 */
	private void uruchomZadanie(Task<ObservableList<Long>> zadanie) {		
		Thread w¹tekWTle = new Thread(zadanie);
		w¹tekWTle.setDaemon(true);
		w¹tekWTle.start();
	}
}