package kontroler;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import kontroler.wejscie.Dokument;
import kontroler.wejscie.DokumentZIzby;
import kontroler.wejscie.Identyfikator;
import procesor.Model;
import procesor.dao.sybase.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.sybase.entity.DokumentZCentralaDokumenty;
import procesor.dao.sybase.entity.DokumentZIzbyDokumenty;
import procesor.raporty.wejscie.Raport;
import procesor.wersja.wejscie.AktualnaWersja;
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
	
	public void wyszukajWCentraliNrAkt(TreeSet<String> numeryAkt) {
		Thread watek = new Thread(new Runnable() {

			@Override
			public void run() {
				ArrayList<Dokument> dokumenty = new ArrayList<>();
				List<Raport> raporty = new ArrayList<>();
				
				for (String numerAkt : numeryAkt) {			
					try {
						List<DokumentZCentralaCntrValidDok> dokumentyZTabeliCntrValidDok = model.findByNrAktInCntrValidDok(numerAkt);
						
						if (dokumentyZTabeliCntrValidDok.size() > 0) {
							for (DokumentZCentralaCntrValidDok dokumentZTabeliCntrValidDok : dokumentyZTabeliCntrValidDok) {
								Dokument dokument = new Dokument(Identyfikator.NUMER_AKT, numerAkt);
								dokumenty.add(dokument);
							
								dokument.setDokumentyZCentralaCntrValidDok(dokumentZTabeliCntrValidDok);

								if (dokumentZTabeliCntrValidDok.getIdentyfikatorDokumentu() != null) {						
									DokumentZCentralaDokumenty dokumentZTabeliDokumenty = model.findByIdDokInDokumenty(dokumentZTabeliCntrValidDok.getIdentyfikatorDokumentu());
									dokument.setDokumentyZCentralaDokumenty(dokumentZTabeliDokumenty);	
								}				
							}
						} else {
							Dokument dokument = new Dokument(Identyfikator.NUMER_AKT, numerAkt);
							dokumenty.add(dokument);
						}
					} catch (Exception ex) {

						break;  //wy�wietla raz komunikat b��du dla listy komunikat�w
					}	
				}
				
				// Wy�wietla odpowiedni raport oraz zapisuje dane "na boku" do ewentualnej analizy
				raporty = model.generujRaporty(dokumenty);
				widok.wy�wietlRaporty(raporty);
				model.zapiszDoAnalizy(raporty);
			}
		});
		
		watek.start();
	}
	
	//
	public void wyszukajWCentraliIdDok(TreeSet<String> identyfikatoryDokument�w) {
		Thread watek = new Thread(new Runnable() {

			@Override
			public void run() {
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
				raporty = model.generujRaporty(dokumenty);
				widok.wy�wietlRaporty(raporty);
				model.zapiszDoAnalizy(raporty);
			}
		});
		
		watek.start();
	}
	
    /**
     * Metoda wyszukuj�ca dokumenty w bazie centralnej po podanych symbolach dokument�w i tworz�ca z otrzymanych danych raporty.
     * 
     * @param symDok
     * 			 Lista numer�w symboli dokument�w po kt�rych nastapi wyszukiwanie w bazie.
     */ 
	public void wyszukajWCentraliSymDok(TreeSet<String> symDok) {
		Thread watek = new Thread(new Runnable() {

			@Override
			public void run() {
				ArrayList<Dokument> dokumenty = new ArrayList<>();
				List<Raport> raporty = new ArrayList<>();
				
				for (String symbolDokumentu : symDok) {
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
				raporty = model.generujRaporty(dokumenty);
				widok.wy�wietlRaporty(raporty);
				model.zapiszDoAnalizy(raporty);
			}
		});
		
		watek.start();
	}
	
	public void wyszukajLokalnieNrAkt(TreeSet<String> numeryAkt) {
		//model.zapiszTESTOWO();
	}
	
	//
	public void wyszukajLokalniePoIdentyfikatorzeDokumentu(TreeSet<String> identyfikatoryDokument�w) {
		Thread watek = new Thread(new Runnable() {

			@Override
			public void run() {
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
								DokumentZIzbyDokumenty dokumentLokalnyZTabeliDokumenty = model.findByIdDokInDokumenty(identyfikatorDokumentu, dokumentZTabeliCntrValidDok.getJednostkaPrzeznaczenia());
								dokumentIzbowy.setDokumentyZIzbyDokumenty(dokumentLokalnyZTabeliDokumenty);
							} 
						}
					} catch (NullPointerException ex) {
						//Wpis do raportu o braku kodu oddzia�u w opisach
					} catch (Exception ex) {
						break;  //wy�wietla raz komunikat b��du dla listy komunikat�w
					}	
				}
				
				// Wy�wietla odpowiedni raport oraz zapisuje dane "na boku" do ewentualnej analizy
				//raporty = model.generujRaporty(dokumenty);
				//widok.wy�wietlRaporty(raporty);
				//model.zapiszDoAnalizy(raporty);
			}
		});
		
		watek.start();
	}
	
	public void wyszukajLokalnieSymDok(TreeSet<String> symboleDokument�w) {
		Thread watek = new Thread(new Runnable() {

			@Override
			public void run() {
				/*ArrayList<Dokument> dokumenty = new ArrayList<>();
				List<Raport> raporty = new ArrayList<>();
				
				for (String symbolDokumentu : symDok) {
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
				raporty = model.generujRaporty(dokumenty);
				widok.wy�wietlRaporty(raporty);
				model.zapiszDoAnalizy(raporty);*/
			}
		});
		
		watek.start();		
	}
}
