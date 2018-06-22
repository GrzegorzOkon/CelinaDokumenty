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
 * @author Grzegorz Oko�
 */
public class Kontroler {
	private Widok widok;
	private Model model;
	
    /**
     * Konstruktor klasy przypisuj�cy referencje do obiekt�w.
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
	
	public void por�wnajWersje() {
		Thread w�tek = new Thread(new Runnable() {
		
			@Override
			public void run() {				
				model.por�wnajWersje();
			}
		});

		w�tek.start();
	}
	
	/**
	 * Metoda wyszukuj�ca dokumenty w bazie centralnej po podanych numerach w�asnych dokument�w i tworz�ca z otrzymanych danych raporty
	 * 
	 * @param numeryAkt - lista numer�w w�asnych dokument�w po kt�rych nast�pi wyszukiwanie w bazie
	 */
	public void wyszukajWCentraliPoNumerachAkt(TreeSet<String> numeryAkt) {
		uruchomZadanie(new WyszukajWCentraliPoNumerachAktZadanie(widok, model, numeryAkt));
	}
	
	/**
	 * Metoda wyszukuj�ca dokumenty w bazie centralnej po podanych identyfikatorach dokument�w i tworz�ca z otrzymanych danych raporty
	 * 
	 * @param identyfikatoryDokument�w - lista identyfikator�w dokument�w po kt�rych nast�pi wyszukiwanie w bazie
	 */
	public void wyszukajWCentraliPoIdentyfikatorachDokument�w(TreeSet<String> identyfikatoryDokument�w) {
		uruchomZadanie(new WyszukajWCentraliPoIdentyfikatorachDokument�wZadanie(widok, model, identyfikatoryDokument�w));
	}
	
    /**
     * Metoda wyszukuj�ca dokumenty w bazie centralnej po podanych symbolach dokument�w i tworz�ca z otrzymanych danych raporty
     * 
     * @param symDok - lista numer�w symboli dokument�w po kt�rych nast�pi wyszukiwanie w bazie
     */ 
	public void wyszukajWCentraliPoSymbolachDokument�w(TreeSet<String> symboleDokument�w) {
		uruchomZadanie(new WyszukajWCentraliPoSymbolachDokument�wZadanie(widok, model, symboleDokument�w));
	}
	
	/**
	 * Metoda wyszukuj�ca dokumenty w bazie centralnej po podanych numerach w�asnych dokument�w, a nast�pnie szukaj�ca na bazie lokalnej i tworz�ca z otrzymanych danych raporty
	 * 
	 * @param numeryAkt - lista numer�w w�asnych dokument�w po kt�rych nast�pi wyszukiwanie w bazie
	 */
	public void wyszukajLokalniePoNumerachAkt(TreeSet<String> numeryAkt) {
		uruchomZadanie(new WyszukajLokalniePoNumerachAktZadanie(widok, model, numeryAkt));
	}
	
	/**
	 * Metoda wyszukuj�ca dokumenty w bazie centralnej po podanych identyfikatorach dokument�w, a nast�pnie szukaj�ca na bazie lokalnej i tworz�ca z otrzymanych danych raporty
	 * 
	 * @param numeryAkt - lista identyfikator�w dokument�w po kt�rych nast�pi wyszukiwanie w bazie
	 */
	public void wyszukajLokalniePoIdentyfikatorachDokument�w(TreeSet<String> identyfikatoryDokument�w) {
		uruchomZadanie(new WyszukajLokalniePoIdentyfikatorachDokument�wZadanie(widok, model, identyfikatoryDokument�w));
	}
	
	/**
	 * Metoda wyszukuj�ca dokumenty w bazie centralnej po podanych symbolach dokument�w, a nast�pnie szukaj�ca na bazie lokalnej i tworz�ca z otrzymanych danych raporty
	 * 
	 * @param numeryAkt - lista symboli dokument�w po kt�rych nast�pi wyszukiwanie w bazie
	 */ 
	public void wyszukajLokalniePoSymbolachDokument�w(TreeSet<String> symboleDokument�w) {
		uruchomZadanie(new WyszukajLokalniePoSymbolachDokument�wZadanie(widok, model, symboleDokument�w));	
	}
	
	/**
	 * Metoda uruchamia d�ugotrwa�e zadania w tle
	 * 
	 * @param zadanie - obiekt typu Task do wykonania
	 */
	private void uruchomZadanie(Task<ObservableList<Long>> zadanie) {		
		Thread w�tekWTle = new Thread(zadanie);
		w�tekWTle.setDaemon(true);
		w�tekWTle.start();
	}
}